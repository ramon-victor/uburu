import React, { useState, useEffect } from "react";
import "./styles/Panel.css";

export interface Line {
    id?: number;
    lineNumber: number;
    content: string;
    file: {
        id?: number;
        name: string;
        path: string;
    };
};

export const Panel = (props: any): JSX.Element => {
    const [content, setContent] = useState([]);

    const formatLines = (lines: Line[]): any[] => {
        const obj: { [key: string]: any } = {};
      
        for (const line of lines) {
            const key = line.file.path;
        
            if (!obj[key]) {
                obj[key] = {
                    path: key,
                    line: []
                };
            }
      
            obj[key].line.push({
                content: line.content,
                number: line.lineNumber
            });
        }
      
        return Object.values(obj);
    };

    const list = formatLines(props.fields);

    useEffect(() => { setContent([]); }, [props.fields]);

    return (
        <>
            <div className="panel" id="files">
                {
                    list.map((item: any, index: number) => {
                        return (
                            <p key={index} onClick={() => setContent(item.line)}>{item.path}</p>
                        )
                    })
                }
            </div>
            <span></span>
            <div className="panel" id="lines">
                {
                    content.map((item: { content: string, number: number }, index: number) => (
                        <div key={index}>
                            {item.number}
                            <p>{item.content}</p>
                        </div>
                    ))
                }
            </div>
        </>
    );
}
