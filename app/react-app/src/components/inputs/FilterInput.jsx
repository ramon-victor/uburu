import Input from "./Input";

class FilterInput extends Input {

    componentDidMount() {
        this.updateHistory("/api/v1/filter");
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
                
                { selected && this.renderHistory() }
            </>
        );
    }
    
}

export default FilterInput;
