import React, { Component } from "react";
import Keyword from "./inputs/Keyword";
import Path from "./inputs/Path";
import Filter from "./inputs/Filter";
import { sendHttpRequest } from "../utils/sendRequest";

class Container extends Component {

    constructor(props) {
        super(props);

        this.state = {
            keywordValue: { keyword: "" },
            filterValue: { filter: "" },
            pathValue: { path: "" }
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
        const date = new Date().toISOString(),
            keywordValue = this.state.keywordValue.keyword,
            filterValue = this.state.filterValue.filter,
            pathValue = this.state.pathValue.path;

        sendHttpRequest(
            "POST",
            "http://localhost:8080/api/v1/keyword",
            JSON.stringify({ date: date, keyword: keywordValue })
        );

        sendHttpRequest(
            "POST",
            "http://localhost:8080/api/v1/filter",
            JSON.stringify({ date: date, filter: filterValue })
        );

        sendHttpRequest(
            "POST",
            "http://localhost:8080/api/v1/path",
            JSON.stringify({ date: date, path: pathValue })
        );
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

                    <Filter
                        title="Filtros / Extensões"
                        id="filter"
                        name="filter"
                        placeholder="*.js; *.jsx;"
                        defaultValue={this.state.filterValue.filter}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "filter")} />

                    <Path
                        title="Caminhos aceitos"
                        id="path"
                        name="path"
                        placeholder="C:\Users\Exemplo"
                        defaultValue={this.state.pathValue.path}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "path")} />
                </div>
                <button onClick={() => this.search()}>Pesquisar</button>
            </>
        );
    }

}

export default Container;
