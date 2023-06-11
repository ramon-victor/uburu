import React from 'react';
import '../styles/ConfirmDialog.css';

export const ConfirmDialog = (props: any): JSX.Element => {
    const handleClick = (del: boolean) => {
        props.setDel(del);
        props.setOpen(false)
    };

    return (
        <div className="confirm-dialog-container">
            <div className='title'>
                <h2>{props.title}</h2>
                <p>{props.text}</p>
            </div>
            <div className='body'>
                <button onClick={() => handleClick(true)}>Sim</button>
                <button id='cancel-button' onClick={() => handleClick(false)}>Não</button>
            </div>
        </div>
    );
}

export default ConfirmDialog;
