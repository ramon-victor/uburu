import Input from "../Input";
import { sendHttpRequest } from "../../utils/sendRequest";
import { GiBroom } from 'react-icons/gi'

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
                        <p key={index} value={item.keyword} onClick={() => this.props.updateDefaultValue(item.keyword)}>
                            {item.keyword}
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

                <button onClick={() => this.deleteHistory("keyword")}>
                    <GiBroom />
                </button>

                { selected && this.renderHistory() }
            </>
        );
    }

}

export default Keyword;
