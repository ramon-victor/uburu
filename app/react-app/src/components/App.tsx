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

export const App = (): JSX.Element => {
  const [path, setPath] = useState<Path>({ path: "", date: new Date() });
  const [filter, setFilter] = useState<Filter>({ filter: "", date: new Date() });
  const [keyword, setKeyword] = useState<Keyword>({ keyword: "", date: new Date() });
  const [subFolders, setSubFolders] = useState(true);
  const [ignoreCase, setIgnoreCase] = useState(true);
  const [fields, setFields] = useState<Line[]>([]);

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
    if (path?.path) {
      PostMethod(
        "http://localhost:8080/api/v1/path",
        JSON.stringify(path)
      );
    }

    if (filter?.filter) {
      PostMethod(
        "http://localhost:8080/api/v1/filter",
        JSON.stringify(filter)
      );
    }

    if (keyword?.keyword) {
      PostMethod(
        "http://localhost:8080/api/v1/keyword",
        JSON.stringify(keyword)
      );
    }
  };

  const search = () => {
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

  return (
    <div className="App">
      <ToastContainer />

      <button className="search-button" onClick={() => search()}>Pesquisar</button>

      <div className="inputs">
        <KeywordInput
          className="fields"
          title="Pesquisa"
          id="keyword"
          name="keyword"
          placeholder="Digite aqui as palavras-chave"
          defaultValue={keyword?.keyword}
          updateDefaultValue={(value: Keyword) => updateDefaultValue(value, "keyword")}
        />

        <FilterInput
          className="fields"
          title="Filtros / Extensões"
          id="filter"
          name="filter"
          placeholder="Exemplo: *.js; *.jsx;"
          defaultValue={filter?.filter}
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
          updateDefaultValue={(value: Path) => updateDefaultValue(value, "path")}
          checked={subFolders}
          setSubFolders={setSubFolders}
        />
      </div>
      
      <div className="results-panel">
        <Panel fields={fields}></Panel>
      </div>
    </div>
  );
};
