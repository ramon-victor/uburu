import { Component } from "react";
import { sendHttpRequest } from "../utils/sendRequest";

class Input extends Component {
    
    constructor(props) {
        super(props);

        this.state = { selected: false };
    }

    componentDidMount() {
        this.updateHistory();
    }

    componentDidUpdate() {
        this.updateHistory();
    }

    updateHistory() {
        throw new Error('Function not implemented');
    }

    deleteHistory(endpoint) {
        sendHttpRequest("DELETE", "http://localhost/8080/api/v1/" + endpoint).catch(err => {
            console.error(err);
        });
    }

    // Fechar a sidebar quando pegar cliques do lado de fora
    outClickListener(id) {
        document.addEventListener('click', (e) => {
            const nav = document.getElementById(id);

            const elementsExist = this.state.selected && nav;
            if (elementsExist && !nav.contains(e.target)) {
                this.setState({ selected: false })
            }
        });
    }

    setSelected() {
        const selected = !this.state.selected;
        this.setState({ selected });
    }

    render() {
        return (<></>);
    }

}

export default Input;
