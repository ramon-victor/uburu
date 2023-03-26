import React from 'react';

class Image extends React.Component {

    render() {
        return (
            <img className='image' id={this.props.id} srcSet={this.props.src} alt={this.props.alt} />
        );
    }

}

export default Image;
