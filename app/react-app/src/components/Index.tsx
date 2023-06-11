import React, { useState, useEffect } from "react";
import { Info } from "./Info";
import './styles/Index.css';
import { DeleteMethod, GetMethod, PostMethod } from "../utils/RestMethods";

interface Index {
    id?: number;
    path: string;
};

export const Index = (props: any): JSX.Element => {
    const [index, setIndex] = useState<Index[]>([]);

    const getIndex = (): void => {
        GetMethod(
            "http://localhost:8080/api/v1/search/indices", (status: number, response: any) => {
                if (status === 200) setIndex(response);
            }
        );
    }

    useEffect(() => getIndex(), []);

    const addToIndex = (): void => {
        GetMethod("http://localhost:8080/api/v1/path/select", (status: number, response: any) => {
            if (status === 200) {
                PostMethod("http://localhost:8080/api/v1/search", JSON.stringify(response));
            }
        });
    };

    const deleteIndex = (): void => {
        DeleteMethod("http://localhost:8080/api/v1/search");
    };

    const info = "Lista dos diretórios indexados.";

    const getName = (path: string): string | undefined => {
        const arr = path.split("\\");
        return `...\\${arr.slice(-2).join("\\")}`;
    };

    return (
        <div className="index">
            <h2>Indice {<Info info={info} />}</h2>
            <div>
                {
                    index.map((path: Index, index: number) => (
                        <p key={index}>{getName(path.path)}</p>
                    ))
                }
            </div>
            <button className="add" onClick={addToIndex}>Novo</button>
            <button className="delete-index" onClick={deleteIndex}>Exluir indice</button>
        </div>
    );
};
