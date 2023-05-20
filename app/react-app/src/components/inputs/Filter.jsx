import Input from "../Input";
import { sendHttpRequest } from "../../utils/sendRequest";
import { GiBroom } from "react-icons/gi";
import { AiOutlineClose } from "react-icons/ai";

class Filter extends Input {

    constructor(props) {
        super(props);

        this.state = { filters: [] };
    }

    updateHistory() {
        sendHttpRequest("GET", "http://localhost:8080/api/v1/filter").then(filters => {
            this.setState({ filters });
        }).catch(err => {
            console.error(err);
        });
    }

    renderHistory() {
        const repositories = this.state.filters;

        return (
            <div>
                {
                    repositories.map((item, index) => (
                        <div key={index}>
                            <p value={item.filter} onClick={() => this.props.updateDefaultValue(item)}>
                                {item.filter}
                            </p>
                            <button onClick={() => this.deleteHistoryOption("filter", item.id)}>
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
                    onChange={(e) => this.handleChange(e, "filter")}
                    onClick={() => this.setSelected()} />

                <button onClick={() => this.props.updateDefaultValue({ filter: "" }, "filter")}>
                    <GiBroom />
                </button>

                {selected && this.renderHistory()}
            </>
        );
    }

}

export default Filter;
