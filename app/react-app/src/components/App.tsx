import React, { useState } from 'react';
import { Panel, Line } from './Panel';
import { PostMethod } from '../utils/RestMethods';
import { Path, PathInput } from './Path';
import { Filter, FilterInput } from './Filter';
import { Keyword, KeywordInput } from './Keyword';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './styles/App.css';
import './styles/Input.css';
import { Index } from './Index';

export const App = (): JSX.Element => {
  const [path, setPath] = useState<Path>({ path: "", date: new Date() });
  const [filter, setFilter] = useState<Filter>({ filter: "", date: new Date() });
  const [keyword, setKeyword] = useState<Keyword>({ keyword: "", date: new Date() });
  const [fields, setFields] = useState<Line[]>([]);
  const [subFolders, setSubFolders] = useState(true);
  const [ignoreCase, setIgnoreCase] = useState(true);
  const [disabled, setDisabled] = useState(false);

  const updateDefaultValue = (value: any, key: string): void => {
    switch (key) {
      case "path":
        setPath(value);
        break;

      case "filter":
        setFilter(value);
        break;

      case "keyword":
        setKeyword(value);
        break;
      
      // React reclama se não tiver opção default
      default: break;
    }
  };

  const submit = (): void => {
    if (path?.path && !path?.id) {
      PostMethod(
        "http://localhost:8080/api/v1/path",
        JSON.stringify(path)
      );
    }

    if (filter?.filter && !filter?.id) {
      PostMethod(
        "http://localhost:8080/api/v1/filter",
        JSON.stringify(filter)
      );
    }

    if (keyword?.keyword && !keyword?.id) {
      PostMethod(
        "http://localhost:8080/api/v1/keyword",
        JSON.stringify(keyword)
      );
    }
  };

  const search = (): void => {
    submit();

    PostMethod(
      "http://localhost:8080/api/v1/search/find",
      JSON.stringify({
          "path": path,
          "filter": filter,
          "keyword": keyword,
          "subFolders": subFolders,
          "ignoreCase": ignoreCase
      }),
      (status: number, response: any) => {
        if (status === 200) {
          const fieldList: Line[] = [];

          response.forEach((element: Line) => {
            fieldList.push(element);
          });

          setFields(fieldList);
        }

        if (status >= 400) {
          const error = "Error: " + response.error,
                msg = "Message: " + response.message;
          console.error(error);
          console.error(msg);

          toast("Ocorreu um erro ao realizar a pesquisa.");
        }
      }
    );
  };

  const cleanAll = (): void => {
    setPath({ path: "", date: new Date() });
    setFilter({ filter: "", date: new Date() });
    setKeyword({ keyword: "", date: new Date() });
    setFields([]);
  }

  return (
    <div className="App">
      <ToastContainer />

      <Index disable={disabled} setDisabled={setDisabled} />

      <div className="inputs" aria-disabled={disabled}>
        <KeywordInput
          className="fields"
          title="Pesquisa"
          id="keyword"
          name="keyword"
          placeholder="Digite aqui as palavras-chave"
          defaultValue={keyword?.keyword}
          disable={disabled}
          updateDefaultValue={(value: Keyword) => updateDefaultValue(value, "keyword")}
        />

        <FilterInput
          className="fields"
          title="Filtros / Extensões"
          id="filter"
          name="filter"
          placeholder="Exemplo: .js; .jsx;"
          defaultValue={filter?.filter}
          disable={disabled}
          updateDefaultValue={(value: Filter) => updateDefaultValue(value, "filter")}
          checked={ignoreCase}
          setIgnoreCase={setIgnoreCase}
        />

        <PathInput
          className="fields"
          title="Caminhos aceitos"
          id="path"
          name="path"
          placeholder="Exemplo: C:\Users\Exemplo"
          defaultValue={path?.path}
          disable={disabled}
          updateDefaultValue={(value: Path) => updateDefaultValue(value, "path")}
          checked={subFolders}
          setSubFolders={setSubFolders}
        />
      </div>

      <button disabled={disabled} className="search-button" onClick={search}>Pesquisar</button>
      <button disabled={disabled} className="clean-button" onClick={cleanAll}>Limpar</button>
      
      <div className="results-panel">
        <Panel disabled={disabled} fields={fields}></Panel>
      </div>
    </div>
  );
};
