import React from "react";

class Anchor extends React.Component {

    render() {
        return (
            <a className="anchor" target="_blank" rel="noreferrer" id={this.props.id} href={this.props.href}>
                {this.props.children}
            </a>
        );
    }

}

export default Anchor;
