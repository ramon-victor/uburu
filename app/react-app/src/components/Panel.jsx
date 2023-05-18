import { Component } from "react";

class Panel extends Component {

    constructor(props) {
        super(props);

        const resultList = props.resultList;
        this.state = { resultList };
    }

    render() {
        const list = this.state.resultList;

        return (
            <div>
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
