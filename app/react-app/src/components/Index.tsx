import React from "react";
import { Path } from "./Path";
import { Info } from "./Info";
import './styles/Index.css';

export const Index = (props: any): JSX.Element => {
    const getIndex = (): Path[] => {
        return [];
    }

    const list = getIndex();
    const info = "Lista dos diretórios indexados. É possível indexar múltiplos diretórios.";

    return (
        <div className="index">
            <h2>Indice {<Info info={info} />}</h2>
            <div>
                {
                    list.map((path: Path) => (
                        <p>{path.path}</p>
                    ))
                }
            </div>
            <button className="add">Novo</button>
            <button className="delete-index">Exluir indice</button>
        </div>
    );
};
