# Instalação do APP do Uburu Search

### Contextualização
Como a pasta node_modules contém muitos arquivos para serem enviados para o Github, ela está no .gitignore e será mantida apenas localmente nos arquivos de cada um. Isso significa que, ao baixar o app, haverão dependências faltando para rodar o projeto. Portanto, é necessário seguir o seguinte passo-a-passo:

1. Acessar a pasta raiz do Uburu
1. Abri o cmd/gitbash/powershel
1. Rodar o comando `npx create-react-app app`
1. Acessar o projeto creat com o comando `cd app`
1. Rodar o comando `npm i electron` para baixar o electron
    - Caso hajam mais dependências, o comando `npm i <dependencia>` deve servir
1. Fazer um git pull no projeto do github para puxar as alterações

#### OBS.: Necessário possui o npm e o node.js

### Inicialização do app
Uma vez que o app esteja instalado na sua máquina e configurado para ser inicializado, para o rodar o app, basta digitar o comando `npm start` e aguardar enquanto o server do react é iniciado
