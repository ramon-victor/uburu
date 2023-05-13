import { Component } from "react";
import { sendHttpRequest } from "../../utils/sendRequest";

class Input extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
            input: '',
            title: props.title,
            placeHolder: props.placeHolder,
            repositories: [],
            selected: false
        };
    }

    // Busca o historico do input
    updateHistory(path) {
		var self = this;
        
        sendHttpRequest("GET", "http://localhost:8080" + path)
            .then(data => {
                self.setState({ repositories: data });
            })
            .catch(err => {
                console.error(`An error occurred! ${err}`);
            });
    }

    setContent(content) {
        this.setState({ input: content });
    }

    setSelected() {
        const selected = !this.state.selected;
        this.setState({ selected });
    }

    outClickListener(title) {
        // Fechar a sidebar quando pegar cliques do lado de fora
        document.addEventListener('click', (e) => {
            const nav = document.getElementById(title);

            // Eu sei que isso aqui tá criminoso, eu peço perdão
            const elementsExist = this.state.selected && nav;
            if (elementsExist && !nav.contains(e.target)) {
                this.setState({ selected: false })
            }
        });
    }

    renderHistory() {
        const repositories = this.state.repositories;

        return (
            <select name="select" id="select">
                {
                    repositories.map((item, index) => (
                        <option key={index} value={item.name}>{item.name}</option>
                    ))
                }
            </select>
        );
    }

    render() {
        return (<></>);
    }

}

export default Input;
