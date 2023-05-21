import Input from "../Input";
import { sendHttpRequest } from "../../utils/sendRequest";
import { GiBroom } from 'react-icons/gi'
import { AiOutlineClose } from "react-icons/ai";
import { MdBrowserUpdated } from "react-icons/md";

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
                        <div key={index}>
                            <p value={item.path} onClick={() => this.props.updateDefaultValue(item)}>
                                {item.path}
                            </p>
                            <button onClick={() => this.deleteHistoryOption("path", item)}>
                                <AiOutlineClose />
                            </button>
                        </div>
                    ))
                }
            </div>
        );
    }

    callPathSelector() {
        sendHttpRequest("GET", "http://localhost:8080/api/v1/path/select")
            .then(path => {
                this.props.updateDefaultValue(path, "path");
            }).catch(err => {
                console.error(err);
            });
    }

    render() {
        const selected = this.state.selected;
        this.outClickListener(this.props.id);

        return (
            <>
                {super.render()}

                <h2>{this.props.title}</h2>
                <div className="input-content">
                    <input
                        type="text"
                        name={this.props.name}
                        id={this.props.id}
                        value={this.props.defaultValue}
                        placeholder={this.props.placeholder}
                        onChange={(e) => this.handleChange(e, "path")}
                        onClick={() => this.setSelected()} />
                        
                    <button className="input-buttons" onClick={() => this.callPathSelector()}>
                        <MdBrowserUpdated />
                    </button>
                    
                    <button className="input-buttons" onClick={() => this.clear(this.props.id, { path: "" }, "path")}>
                        <GiBroom />
                    </button>
                </div>

                {selected && this.renderHistory()}
            </>
        );
    }

}

export default Path;
