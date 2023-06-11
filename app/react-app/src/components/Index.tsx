import React, { useState, useEffect } from "react";
import { Info } from "./Info";
import './styles/Index.css';
import { DeleteMethod, GetMethod, PostMethod } from "../utils/RestMethods";
import ConfirmDialog from "../utils/ConfirmDialog";

interface IndexInterface {
    id?: number;
    path: string;
};

export const Index = (): JSX.Element => {
    const [indexList, setIndexList] = useState<IndexInterface[]>([]);
    const [open, setOpen] = useState(false);
    const [del, setDel] = useState(false);

    const getIndex = (): void => {
        GetMethod(
            "http://localhost:8080/api/v1/search/indices", (status: number, response: any) => {
                if (status === 200) setIndexList(response);
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
        setOpen(true);

        if (del) {
            DeleteMethod("http://localhost:8080/api/v1/search");
        }
    };

    const info = "Lista dos diretórios indexados.";

    const getName = (path: string): string | undefined => {
        const arr = path.split("\\");
        return `...\\${arr.slice(-2).join("\\")}`;
    };

    return (
        <>
            <div className="index">
                <h2>Indice {<Info info={info} />}</h2>
                <div>
                    {
                        indexList.map((path: IndexInterface, i: number) => (
                            <p key={i}>{getName(path.path)}</p>
                        ))
                    }
                </div>
                <button className="add" onClick={addToIndex}>Novo</button>
                <button className="delete-index" onClick={deleteIndex}>Exluir indice</button>
            </div>
            {
                open && <ConfirmDialog
                            title="Tem certeza de que quer excluir?"
                            text="Essa ação apagará o indice inteiro."
                            setOpen={setOpen}
                            setDel={setDel}
                        />
            }
        </>
    );
};
