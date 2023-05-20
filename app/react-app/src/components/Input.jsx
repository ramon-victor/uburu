import { Component } from "react";
import { sendHttpRequest } from "../utils/sendRequest";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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

    handleChange(evt, key) {
        const value = evt.target.value;

        switch (key) {
            case "keyword":
                this.props.updateDefaultValue({ keyword: value }, key);
                break;
            case "filter":
                this.props.updateDefaultValue({ filter: value }, key);
                break;
            case "path":
                this.props.updateDefaultValue({ path: value }, key);
                break;

            // React reclama se não tiver opção default
            default: break;
        }
    }

    deleteHistoryOption(endpoint, key) {
        const url = "http://localhost:8080/api/v1/" + endpoint + "/" + key;
        const params = { id: key };

        sendHttpRequest("DELETE", url, params)
            .then(response => {
                if (response.status === 202) {
                    toast("Item do histórico deletado com sucesso!");
                }

                if (response.status >= 400) {
                    const msg = "Error: " + response.error + "\nMessage: " + response.message;
                    throw new Error(msg);
                }
            }).catch(err => {
                toast("Ocorreu um erro ao deletar o item do histórico.")
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
        return (
            <>
                <ToastContainer />
            </>
        );
    }

}

export default Input;
