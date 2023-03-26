import React from 'react';

class TextInput extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            history: []
        };
    }

    render() {
        return (
            <input className='text-input-field' type='text'
                id={this.props.id}
                name={this.props.name}
                onClick={() => this.props.onClick()}
            />
        );
    }

}

export default TextInput;
