import '../styles/App.css';
import KeywordInput from './inputs/KeywordInput';
import PathInput from './inputs/PathInput';
import FilterInput from './inputs/FilterInput';

function App() {
  return (
    <div className="App">
      <KeywordInput title="Pesquisa" placeHolder="Digite a palavra chave" />
      <PathInput title="Diretório" placeHolder="Digite o diretório de pesquisa" />
      <FilterInput title="Filtros" placeHolder="Digite o diretório de pesquisa" />
    </div>
  );
}

export default App;
