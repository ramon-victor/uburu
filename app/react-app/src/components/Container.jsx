import React, { Component } from "react";
import Keyword from "./inputs/Keyword";
import Path from "./inputs/Path";
import Filter from "./inputs/Filter";

class Container extends Component {

    constructor(props) {
        super(props);

        this.state = {
            keywordValue: "",
            filterValue: "",
            pathValue: ""
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
            default: break;
        }
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
                        defaultValue={this.state.keywordValue}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "keyword")} />

                    <Filter
                        title="Filtros / Extensões"
                        id="filter"
                        name="filter"
                        placeholder="*.java; *.js; *.jsx;"
                        defaultValue={this.state.filterValue}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "filter")} />

                    <Path
                        title="Caminhos aceitos"
                        id="path"
                        name="path"
                        placeholder="C:\Users\Exemplo"
                        defaultValue={this.state.pathValue}
                        updateDefaultValue={(value) => this.updateDefaultValue(value, "path")} />
                </div>                
            </>
        );
    }

}

export default Container;
