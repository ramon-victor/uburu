# Uburu

## Sistema criado na disciplina **MÉTODOS, TÉCNICAS E FERRAMENTAS DE ENGENHARIA DE SOFTWARE**

## 1. Contexto do projeto

O projeto de software proposto tem como objetivo resolver um problema comum no desenvolvimento de software: a duplicação de código. Esse problema aumenta a complexidade do sistema e gera arquivos inúteis, o que pode afetar negativamente a eficiência e a qualidade do software.

A solução proposta envolve a criação de uma ferramenta que permita identificar o código já existente que solucione um determinado problema em um contexto específico de desenvolvimento. Para isso, será necessário desenvolver algoritmos de comparação de código que possam analisar uma grande quantidade de arquivos de código-fonte e identificar soluções similares ou idênticas.

A ferramenta proposta terá a capacidade de localizar e destacar trechos de código duplicados em diferentes arquivos, além de permitir a visualização e a comparação desses trechos lado a lado. Além disso, a ferramenta poderá ser configurada para realizar buscas em diferentes repositórios de código-fonte, permitindo que desenvolvedores possam identificar soluções similares em diferentes projetos.

Com essa ferramenta, os desenvolvedores poderão identificar rapidamente o código duplicado e substituí-lo por soluções mais eficientes e elegantes, reduzindo a complexidade do sistema e melhorando a qualidade do software. Além disso, a ferramenta também poderá ser utilizada como uma ferramenta de aprendizado, permitindo que desenvolvedores iniciantes possam estudar diferentes soluções para um mesmo problema e aprender com as boas práticas utilizadas pelos desenvolvedores mais experientes.

## 2. Requisitos

### 2.1. Requisitos funcionais
1. O aplicativo deve permitir ao usuário especificar o diretório a ser pesquisado.
1. O aplicativo deve permitir ao usuário especificar um ou mais termos de busca.
1. O aplicativo deve retornar uma lista de resultados que correspondam aos termos de busca.
1. O aplicativo deve permitir ao usuário abrir e visualizar os arquivos que contêm os resultados da pesquisa.
1. O aplicativo deve permitir ao usuário salvar os resultados da pesquisa em um arquivo ou imprimir a lista de resultados.
1. O aplicativo deve permitir que o usuário refine a pesquisa usando filtros, como tipo de arquivo, data de modificação e tamanho do arquivo.
1. O aplicativo deve ser capaz de pesquisar texto em uma ampla variedade de formatos de arquivo, incluindo PDFs, planilhas, documentos do Word, arquivos de texto, apresentações, e-mails, etc.
1. O aplicativo deve exibir os resultados da pesquisa em uma lista com informações adicionais, como o nome do arquivo, localização e data de modificação.
1. O aplicativo deve permitir que o usuário navegue pelos resultados da pesquisa e abra os arquivos relevantes.
1. O aplicativo deve manter um registro do histórico de pesquisa para que o usuário possa acessar facilmente pesquisas anteriores.

### 2.2. Requisitos não funcionais
1. O aplicativo deve ser rápido e eficiente na pesquisa e retorno dos resultados.
1. O aplicativo deve ser fácil de usar, com uma interface intuitiva e amigável para o usuário.
1. O aplicativo deve ser capaz de lidar com grandes quantidades de dados e arquivos sem falhas ou erros.
1. O aplicativo deve ser seguro e proteger a privacidade dos usuários, não acessando arquivos sem permissão e não coletando dados pessoais sem consentimento explícito.
1. O aplicativo deve ser seguro e não comprometer a privacidade ou a segurança dos dados do usuário.
1. Ao menos 70% do código deve ser cobrido por testes unitários.

## 3. Diagrama de casos de uso

## 4. Diagrama de classes

## 5. Definição de tecnologias

### 5.1. Springboot
1. Facilidade para a construção de APIs: O Spring Boot é uma estrutura de desenvolvimento que foi projetada especificamente para facilitar a criação de APIs. Com o Spring Boot, você pode criar APIs de alta qualidade de forma rápida e fácil, usando uma variedade de ferramentas e recursos disponíveis.
1. Grande quantidade de recursos disponíveis: O Spring Boot é uma plataforma extremamente poderosa que oferece uma grande quantidade de recursos e funcionalidades que podem ser utilizados em uma ampla gama de projetos. Com o Spring Boot, você tem acesso a uma biblioteca extensa e diversificada de componentes, que podem ser facilmente integrados em projetos existentes, reduzindo o tempo e o esforço necessários para o desenvolvimento de novos recursos.
1. Familiaridade com a linguagem Java: Os envolvidos no projeto tem conhecimento e anos de experiência em desenvolvimento utilizando a linguagem Java. Fora isso, todos já participaram do desenvolvimento de um projeto utilizando o framework SPringboot.
1. Equilíbrio entre poder computacional e a dificuldade no desenvolvimento: O Spring Boot é altamente escalável e fornece um bom equilíbrio entre poder computacional e complexidade de desenvolvimento. Ele é leve, rápido e pode ser facilmente configurado para se adaptar às necessidades do seu projeto. Com o Spring Boot, você pode ter certeza de que sua aplicação terá um bom desempenho e será fácil de manter e atualizar.

### 5.2. Electron JS
1. Cross-platform: O Electron JS é um framework cross-platform que permite criar aplicativos para desktop que podem ser executados em vários sistemas operacionais, incluindo Windows, macOS e Linux.
1. Baseado em tecnologias web: O Electron JS é baseado em tecnologias web, como HTML, CSS e JavaScript. Isso significa que os envolvidos no projeto, que já estão familiarizados com essas tecnologias, podem facilmente criar aplicativos para desktop sem precisar aprender uma nova linguagem ou tecnologia. Além disso, como o Electron JS utiliza tecnologias web, é possível reutilizar muito do código de um aplicativo web existente para criar um aplicativo para desktop e vice-versa.
1. Grande comunidade: O Electron JS é uma framework open-source com uma grande comunidade de desenvolvedores ativos. Isso significa que há uma grande quantidade de recursos disponíveis, incluindo documentação, bibliotecas e exemplos de código, o que facilita o desenvolvimento de aplicativos.
1. Funcionalidades nativas: O Electron JS permite que os desenvolvedores criem aplicativos para desktop com funcionalidades nativas, como acesso a arquivos locais, notificações e integração com o sistema operacional. Isso permite que os aplicativos criados com o Electron JS tenham um desempenho semelhante ao de aplicativos nativos.

## 6. Dependências do projeto
