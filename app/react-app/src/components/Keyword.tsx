import React, { useState, useEffect } from "react";
import { AiOutlineClose } from "react-icons/ai";
import { GiBroom } from "react-icons/gi";
import { DeleteMethod, GetMethod } from "../utils/RestMethods";
import { toast } from 'react-toastify';
import { Info } from "./Info";

export interface Keyword {
    keyword: string;
    id?: number;
    date?: Date;
};

export const KeywordInput = (props: any): JSX.Element => {
    const [selected, setSelected] = useState(false);
    const [keyword, setKeyword] = useState<Keyword[]>([]);

    const updateHistory = (): void => {
        GetMethod("http://localhost:8080/api/v1/keyword", (status: number, response: any) => {
            if (status === 200) setKeyword(response);
        });
    };

    useEffect(() => { updateHistory(); });

    const deleteHistoryOption = (param: Keyword): void => {
        const params = JSON.stringify(param);

        DeleteMethod("http://localhost:8080/api/v1/keyword", params, (status: number, response: any) => {
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
        if (keyword.length === 0) return (<div></div>);

        return (
            <div className="history">
                {
                    keyword.map((item: Keyword, index: number) => (
                        <div key={index}>
                            <p defaultValue={item.keyword} onClick={() => props.updateDefaultValue(item, "keyword")}>
                                {item.keyword}
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

    const info = "Este campo contém as palavras que devem ser buscadas.";

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
                        const keyword = {
                            keyword: e.target.value,
                            date: new Date()
                        };
                        props.updateDefaultValue(keyword, "keyword");
                    }}
                    onClick={() => setSelected(true)} />

                <button className="input-buttons" onClick={() => props.updateDefaultValue({ keyword: "" }, "keyword")}>
                    <GiBroom />
                </button>
            </div>

            {selected && renderHistory()}
        </>
    );
};
