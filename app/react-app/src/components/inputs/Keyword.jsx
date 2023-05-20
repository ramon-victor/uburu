import Input from "../Input";
import { sendHttpRequest } from "../../utils/sendRequest";
import { GiBroom } from 'react-icons/gi'
import { AiOutlineClose } from "react-icons/ai";

class Keyword extends Input {

    constructor(props) {
        super(props);

        this.state = { keywords: [] };
    }

    updateHistory() {
        sendHttpRequest("GET", "http://localhost:8080/api/v1/keyword").then(keywords => {
            this.setState({ keywords });
        }).catch(err => {
            console.error(err);
        });
    }

    renderHistory() {
        const repositories = this.state.keywords;

        return (
            <div>
                {
                    repositories.map((item, index) => (
                        <div key={index}>
                            <p value={item.keyword} onClick={() => this.props.updateDefaultValue(item)}>
                                {item.keyword}
                            </p>
                            <button onClick={() => this.deleteHistoryOption("keyword", item.id)}>
                                <AiOutlineClose />
                            </button>
                        </div>
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
                {super.render()}

                <h2>{this.props.title}</h2>
                <input
                    type="text"
                    name={this.props.name}
                    id={this.props.id}
                    defaultValue={this.props.defaultValue}
                    placeholder={this.props.placeholder}
                    onChange={(e) => this.handleChange(e, "keyword")}
                    onClick={() => this.setSelected()} />

                <button onClick={() => this.props.updateDefaultValue({ keyword: "" }, "keyword")}>
                    <GiBroom />
                </button>

                {selected && this.renderHistory()}
            </>
        );
    }

}

export default Keyword;
