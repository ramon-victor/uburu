import React, { Component } from "react";
import Keyword from "./inputs/Keyword";
import Path from "./inputs/Path";
import Filter from "./inputs/Filter";
import Panel from "./Panel";
import { sendHttpRequest } from "../utils/sendRequest";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class Container extends Component {

    constructor(props) {
        super(props);

        const date = new Date().toISOString();
        this.state = {
            keywordValue: { keyword: "", date: date },
            filterValue: { filter: "", date: date },
            pathValue: { path: "", date: date },
            subfolders: true,
            ignoreCase: true,
            files: [],
            lines: []
        };
    }

    updateDefaultValue(value, key) {
        switch (key) {
            case "keyword":
                this.setState({ keywordValue: value });
                break;

            case "filter":
                this.setState({ filterValue: value });
                break;
                
            case "path":
                this.setState({ pathValue: value });
                break;
            
            // React reclama se não tiver opção default
            default: break;
        }
    }

    submit() {
        if (this.state.keywordValue.keyword && !this.state.keywordValue.id) {
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/keyword",
                JSON.stringify(this.state.keywordValue)
            );
        }

        if (this.state.filterValue.filter && !this.state.filterValue.id) {
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/filter",
                JSON.stringify(this.state.filterValue)
            );
        }

        if (this.state.pathValue.path && !this.state.pathValue.id) {
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/path",
                JSON.stringify(this.state.pathValue)
            );
        }
    }

    changeSubfolders() {
        const subfolders = !this.state.subfolders;
        this.setState({ subfolders });
    }

    changeIgnoreCase() {
        const ignoreCase = !this.state.ignoreCase;
        this.setState({ ignoreCase });
    }

    search() {
        this.submit();

        if (this.state.keywordValue.keyword) {
            var self = this;
            
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/search/find",
                JSON.stringify({
                    "path": this.state.pathValue,
                    "filter": this.state.filterValue,
                    "keyword": this.state.keywordValue,
                    "subFolders": this.state.subfolders,
                    "ignoreCase": this.state.ignoreCase
                })
            ).then(response => {
                debugger;
                if (response?.status === 200) {
                    const files = [], lines = [];

                    response.forEach(element => {
                        lines.push(element.content);
                        files.push(element.file.name);
                    });

                    self.setState({ lines });
                    self.setState({ files });
                }

                if (response.status >= 400) {
                    const msg = "Error: " + response.error + "\nMessage: " + response.message;
                    throw new Error(msg);
                }
            }).catch(err => {
                toast("Ocorreu um erro ao realizar a pesquisa.")
                console.error(err);
            });

            this.forceUpdate();
        }
    }

    render() {
        return (
            <>
                <ToastContainer />
                <div className="inputs">
                    <Keyword
                        title="Pesquisa"
                        id="keyword"
                        name="keyword"
                        placeholder="Digite aqui as palavras-chave"
                        defaultValue={this.state.keywordValue.keyword}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "keyword")} />
                    <label htmlFor="ignore-case">Considerar capitalização</label>
                    <input
                        type="checkbox"
                        checked={this.state.ignoreCase}
                        onChange={() => this.changeIgnoreCase()}
                        name="ignore-case" id="ignore-case" />

                    <Filter
                        title="Filtros / Extensões"
                        id="filter"
                        name="filter"
                        placeholder="*.js; *.jsx;"
                        defaultValue={this.state.filterValue.filter}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "filter")} />
                    <label htmlFor="subfolders">Considerar subrepositórios</label>
                    <input
                        type="checkbox"
                        checked={this.state.subfolders}
                        onChange={() => this.changeSubfolders()}
                        name="subfolders" id="subfolders" />

                    <Path
                        title="Caminhos aceitos"
                        id="path"
                        name="path"
                        placeholder="C:\Users\Exemplo"
                        defaultValue={this.state.pathValue.path}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "path")} />
                </div>
                <button onClick={() => this.search()}>Pesquisar</button>
                <br />

                <Panel resultList={this.state.files}></Panel>
                <Panel resultList={this.state.lines}></Panel>
            </>
        );
    }

}

export default Container;
