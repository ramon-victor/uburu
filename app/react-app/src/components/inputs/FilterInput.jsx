import Input from "./Input";

class FilterInput extends Input {

    componentDidMount() {
        this.apiPath = "localhost:8080/api/v1/filter";
        super.componentDidMount();
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
                    value={this.state.input}
                    onClick={() => {this.setSelected()}} />
                <div>
                {
                    selected && repositories?.map((item, index) => (
                        <p key={index}>{item.name}</p>
                    ))
                }
                </div>
            </>
        );
    }
    
}

export default FilterInput;
