import Input from "../Input";
import { sendHttpRequest } from "../../utils/sendRequest";
import { GiBroom } from 'react-icons/gi'

class Path extends Input {

    constructor(props) {
        super(props);

        this.state = { paths: [] };
    }

    updateHistory() {
        sendHttpRequest("GET", "http://localhost:8080/api/v1/path").then(paths => {
            this.setState({ paths });
        }).catch(err => {
            console.error(err);
        });
    }

    renderHistory() {
        const repositories = this.state.paths;

        return (
            <div>
                {
                    repositories.map((item, index) => (
                        <p key={index} value={item.path} onClick={() => this.props.updateDefaultValue(item.path)}>
                            {item.path}
                        </p>
                    ))
                }
            </div>
        );
    }

    render() {
        const selected = this.state.selected;
        this.outClickListener(this.props.id);

        return (
            <>
                <h2>{this.props.title}</h2>
                <input
                    type="text"
                    name={this.props.name}
                    id={this.props.id}
                    defaultValue={this.props.defaultValue}
                    placeholder={this.props.placeholder}
                    onClick={() => {this.setSelected()}} />

                <button onClick={() => this.deleteHistory("path")}>
                    <GiBroom />
                </button>

                { selected && this.renderHistory() }
            </>
        );
    }

}

export default Path;
