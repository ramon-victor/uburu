import React from "react";
import Input from "./Input";

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
                    defaultValue={this.state.input}
                    onClick={() => {this.setSelected()}} />

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
