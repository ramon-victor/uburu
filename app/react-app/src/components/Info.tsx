import React, { useState } from "react";
import { MdOutlineInfo } from 'react-icons/md';
import './styles/Info.css';

export const Info = (props: any): JSX.Element => {
    const [hover, setHover] = useState(false);

    const onMouseOver = (): void => {
        setHover(true);
    }

    const onMouseOut = (): void => {
        setHover(false);
    }

    const renderInfo = (info: string): JSX.Element => {
        return (
            <p>{info}</p>
        );
    }

    return (
        <span className="info" onMouseOver={onMouseOver} onMouseOut={onMouseOut}>
            { <MdOutlineInfo /> }
            { hover && renderInfo(props.info) }
        </span>
    );
};
