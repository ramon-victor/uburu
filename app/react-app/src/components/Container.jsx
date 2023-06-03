import React, { Component } from "react";
import Keyword from "./inputs/Keyword";
import Path from "./inputs/Path";
import Filter from "./inputs/Filter";
import Panel from "./Panel";
import { sendHttpRequest } from "../utils/sendRequest";

class Container extends Component {

    constructor(props) {
        super(props);

        this.state = {
            keywordValue: { keyword: "" },
            filterValue: { filter: "" },
            pathValue: { path: "" },
            subfolders: true,
            ignoreCase: true
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
        if (this.state.keywordValue.keyword) {
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/keyword",
                JSON.stringify(this.state.keywordValue)
            );
        }

        if (this.state.filterValue.filter) {
            sendHttpRequest(
                "POST",
                "http://localhost:8080/api/v1/filter",
                JSON.stringify(this.state.filterValue)
            );
        }

        if (this.state.pathValue.path) {
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

        // TODO: Método para preencher os quadros com o resultado da pesquisa
    }

    render() {
        return (
            <>
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

                <Panel resultList={[1, 2, 3, 4, 5]}></Panel>
                <Panel resultList={[1, 2, 3, 4, 5]}></Panel>
            </>
        );
    }

}

export default Container;
