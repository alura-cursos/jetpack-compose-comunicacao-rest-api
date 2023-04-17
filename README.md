# Anyflix

![Imagem de capa indicando ser o curso Jetpack Compose: comunicação com REST API](https://user-images.githubusercontent.com/8989346/231193820-decade74-da1e-467d-8480-f0651d403bd8.png)

## 🔨 Funcionalidades do projeto

App para simular uma plataforma de streaming de videos. O Anyflix oferece as seguintes telas:

- `Início`: mostra todos os filmes em seções
- `Minha lista`: exibe todos os filmes adicionados na minha lista e permite remover os filmes da minha lista
- `Detalhes do filme`: apresenta as informações do filme, como imagem, título, ano e enredo. Também, possibilita a adição ou remoção do filme na minha lista

![App Anyflix em execução](https://user-images.githubusercontent.com/8989346/231199569-89800b65-30c7-4eb2-b93d-c6732a044aee.gif)

## 🎯 Desafios

- Buscando endereço pelo CEP automaticamente

![App em execução exibindo o formulário com os campos para endereço, ao preencher o campo CEP, após 2 segundos, automaticamente o campo de logradouro, bairro, cidade e estado são preenchidos automaticamente](https://user-images.githubusercontent.com/8989346/232496380-398a1032-5300-4913-8b87-6d6927556c25.gif)


## ✔️ Técnicas e tecnologias utilizadas

O App foi desenvolvido com as seguintes técnicas e 

- `Hilt`: injeção de dependência
- `Room`: salvar e buscar informação do banco de dados local
- `ViewModel` e `uiState`: gerenciamento de estado
- `Coroutine` e `Flow`: rodar as operações de maneira assíncrona e reativas
- `Retrofit`: realizar requisições HTTP
- `Repositório`: responsável em lidar com fontes de dados distintas: banco de dados (Room) e REST API (Retrofit)
  - `Banco de dados`: Room
  - `REST API`: Retrofit
- `Jetpack Compose`: implementação da interface de usuário

## 📁 Acesso ao projeto

Você pode [acessar o código fonte do projeto](https://github.com/alura-cursos/jetpack-compose-comunicacao-rest-api/tree/aula-4) ou [baixá-lo](https://github.com/alura-cursos/jetpack-compose-comunicacao-rest-api/archive/refs/heads/aula-4.zip).

## 🛠️ Abrir e rodar o projeto

Após baixar o projeto, você pode abrir com o Android Studio. Para isso, na tela de launcher clique em:

Open an Existing Project (ou alguma opção similar)
Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo)
Por fim clique em OK
O Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆

<!-- ## 📚 Mais informações do curso

**Faça um CTA (_call to action_) para o curso do projeto**
