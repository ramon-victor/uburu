# Instalação do APP do Uburu Search

### Contextualização
Como a pasta node_modules contém muitos arquivos para serem enviados para o Github, ela está no .gitignore e será mantida apenas localmente nos arquivos de cada um. Isso significa que, ao baixar o app, haverão dependências faltando para rodar o projeto. Portanto, é necessário seguir o seguinte passo-a-passo:

1. Acessar a localização onde você planeja inicializar o projeto
1. Rodar os seguintes comando em sequência:
    - `mkdir uburu`
    - `cd uburu`
    - `mkdir app`
    - `cd app`
1. Na pasta _app_ brir um dos seguintes: `cmd|gitbash|powershel`
1. Rodar o comando `npx create-react-app react-app`. É importante que o nome do projeto e sua estrutura seja sempre a mesma, para não haver problemas de instalação e com as dependências do projeto
1. Acessar o projeto react com o comando `cd react-app`
1. Rodar o comando `npm i electron` para baixar o _electron_
1. Para a dependência _concurrently_, rode o comando `yarn add concurrently --save-dev`
1. Para as demais dependências, usar o comando `npm i <dependencia>` deve servir
1. Retornar à pasta do uburu
1. Fazer um `git pull` no projeto do github para puxar as alterações

#### OBS.: Necessário possui o npm e o node.js

### Inicialização do app
Uma vez que o app esteja instalado na sua máquina e configurado para ser inicializado, para o rodar o app, basta digitar o comando `npm start` e aguardar enquanto o server do react é iniciado
