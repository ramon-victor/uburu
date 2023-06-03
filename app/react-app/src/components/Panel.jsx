import { Component } from "react";
import "./styles/Panel.css";

class Panel extends Component {

    render() {
        const list = this.props.resultList;

        return (
            <div className="panel">
                {
                    list.map((item, index) => (
                        <p key={index}>{item}</p>
                    ))
                }
            </div>
        );
    }

}

export default Panel;
