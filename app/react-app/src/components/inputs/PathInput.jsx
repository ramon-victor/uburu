import React from "react";
import Input from "./Input";
import { sendHttpRequest } from "../../utils/sendRequest";

class PathInput extends Input {

    constructor(props) {
        super(props);
        this.state.subFolders = true;
    }

    componentDidMount() {
        this.updateHistory("/api/v1/path");
    }

    handleSubFolders() {
        const subFolders = !this.state.subFolders;
        this.setState({ subFolders });
    }

    selectPath() {
        sendHttpRequest("GET", "http://localhost:8080/api/v1/path/select")
        .then(data => {
            this.setState({ input: data.path });
        })
        .catch(err => {
            console.log(err);
        })
    }

    render() {
        const title = this.state.title;
        const selected = this.state.selected && this.state.repositories.length >= 1;
        this.outClickListener(title);

        return (
            <>
                <h2>{title}</h2>
                <input
                    type="text"
                    name={title}
                    id={title}
                    value={this.state.input}
                    onClick={() => {this.setSelected()}} />

                <button className="select-path" onClick={() => this.selectPath()}>
                    Select path
                </button>

                <input
                    type="checkbox"
                    name="subfolders"
                    id="subfolders"
                    onClick={() => {this.handleSubFolders()}} />
                <label htmlFor="subfolders">Subfolders</label>

                { selected && this.renderHistory() }
            </>
        );
    }
    
}

export default PathInput;
