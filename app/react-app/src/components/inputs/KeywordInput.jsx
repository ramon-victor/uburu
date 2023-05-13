import Input from "./Input";

class KeywordInput extends Input {

    componentDidMount() {
        this.updateHistory("/api/v1/keyword");
    }

    render() {
        const title = this.state.title;
        const selected = this.state.selected;
        const repositories = this.state.repositories;
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
                <div>
                {
                    selected && repositories?.map((item, index) => (
                        <p key={index} onClick={() => {this.setContent(item.name)}}>{item.name}</p>
                    ))
                }
                </div>
            </>
        );
    }

}

export default KeywordInput;
