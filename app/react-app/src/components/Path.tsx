import React, { useState, useEffect } from "react";
import { GiBroom } from 'react-icons/gi'
import { AiOutlineClose } from "react-icons/ai";
import { MdBrowserUpdated } from "react-icons/md";
import { DeleteMethod, GetMethod } from "../utils/RestMethods";
import { toast } from 'react-toastify';
import { Info } from "./Info";

export interface Path {
    path: string;
    id?: number;
    date?: Date;
};

export const PathInput = (props: any): JSX.Element => {
    const [selected, setSelected] = useState(false);
    const [path, setPath] = useState<Path[]>([]);

    const updateHistory = (): void => {
        GetMethod("http://localhost:8080/api/v1/path", (status: number, response: any) => {
            if (status === 200) setPath(response);
        });
    };

    useEffect(() => { updateHistory(); });

    const callPathSelector = (): void => {
        GetMethod("http://localhost:8080/api/v1/path/select", (status: number, response: any) => {
            if (status === 200) {
                setPath(response);
                props.updateDefaultValue(response, "path");
            }
        });
    };

    const deleteHistoryOption = (param: Path): void => {
        const params = JSON.stringify(param);

        DeleteMethod("http://localhost:8080/api/v1/path", params, (status: number, response: any) => {
            if (status === 202) {
                toast("Item do histórico deletado com sucesso!");
            }

            if (status >= 400) {
                const error = "Error: " + response.error,
                    msg = "Message: " + response.message;
                console.error(error);
                console.error(msg);

                toast("Ocorreu um erro ao deletar o item do histórico.");
            }
        });
    };

    const renderHistory = (): JSX.Element => {
        if (path.length === 0) return (<div></div>);

        return (
            <div className="history">
                {
                    path.map((item: Path, index: number) => (
                        <div key={index}>
                            <p defaultValue={item.path} onClick={() => props.updateDefaultValue(item, "path")}>
                                {item.path}
                            </p>
                            <button onClick={() => deleteHistoryOption(item)}>
                                <AiOutlineClose />
                            </button>
                        </div>
                    ))
                }
            </div>
        );
    };

    document.addEventListener('click', (e) => {
        const nav = document.getElementById(props.id);

        const elementsExist = selected && nav;
        if (elementsExist && !nav.contains(e.target as Node)) {
            setSelected(false);
        }
    });

    const info = "Este campo contém os repositórios aceitos na pesquisa. "
    + "Para selecionar múltiplos repositórios, utilize \";\" e para "
    + "definir repositórios inválidos, utilize \"!\".";

    return (
        <>
            <h2>
                {props.title}
                {<Info info={info} />}
            </h2>
            <div className="input-content">
                <input
                    className={props.className}
                    type="text"
                    name={props.name}
                    id={props.id}
                    value={props.defaultValue}
                    placeholder={selected ? "" : props.placeholder}
                    onChange={(e) => {
                        const path = {
                            path: e.target.value,
                            date: new Date()
                        };
                        props.updateDefaultValue(path, "path");
                    }}
                    onClick={() => setSelected(true)} />
                    
                <button className="select-button" onClick={() => callPathSelector()}>
                    <MdBrowserUpdated />
                </button>
                
                <button className="input-buttons" onClick={() => props.updateDefaultValue({ path: "" }, "path")}>
                    <GiBroom />
                </button>
                
                <label htmlFor="subFolders">Considerar subdiretórios</label>
                <input
                    type="checkbox"
                    checked={props.checked}
                    onChange={() => { props.setSubFolders(!props.checked) }}
                    name="subFolders" id="subFolders" />
            </div>

            {selected && renderHistory()}
        </>
    );
};
