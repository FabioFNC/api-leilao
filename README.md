<div align="center">
	<h1>Leilão API REST</h1>
  
  <p align="center">
    Essa aplicação foi feita para ajudar aqueles que ainda estão com alguma dúvida, sobre o funcionamento de um API REST em Spring. <br>
    Já que eu tive várias duvidás ao decorrere do desenvolvimento. Após obsevar varias lacunas no meu aprendizado decidir correr atrás do prejeuizo, e assim me deu vontade de compartilhar esse projeto. <br>
  </p>

<h4 align="center"> 
	🚧   Projeto em construção...  🚧
  <br><br>
  
  ![Operario](https://tenor.com/view/oh-yeah-gif-23554938.gif)
  
</h4>
</div>

## 🛠 Ferramentas

- [☕ Java](https://www.oracle.com/java/technologies/downloads/)
- [🍃 Spring](https://spring.io/)
- [🐘 Postgre](https://www.postgresql.org/)

## 📌 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java](https://www.oracle.com/java/technologies/downloads/), [PostgreSQL](https://www.postgresql.org/) e o [Mavem](https://maven.apache.org/download.cgi). 
Além disto é bom ter um editor para trabalhar com o código como o [IntelliJ](https://www.jetbrains.com/pt-br/idea/download/#section=window) por exemplo.

## 🎲 Clonando o projeto

```bash
# Clone este repositório
$ git clone https://github.com/FabioFNC/api-leilao.git

# Lembre-se que o servidor inciará na porta:8080 - acesse <http://localhost:8080>
# Caso queira trocar a porta, basta trocar o server.port=8080 para uma do seu interesse no application.properties.
```

## 🤔 Como iniciar a aplicação?

Vá ao diretório raiz da aplicação e dê um: ```mvn clean package```, assim a aplicação iniciará no profile dev, onde o banco de dados será populado com o ```import.sql``` e terá varias outras facilidades para o desenvolvimento.

Caso queira trocar para o profile prod por algum motivo, vá ao diretório raiz da aplicação e dê um: ```mvn -Pprod clean package```, apos isso vá para a pasta target é passe esses parâmetros no terminal: 

```Java -jar -DDATABASE_URL=<URL do seu BD> -DDATABASE_USER=<Usuario do BD> -DDATABASE_PASSWORD=<Senha do usuario> -DJWT_SECRET=<Chave para validaçao do token> -DJWT_EXPIRATION=<Tempo da duração do token> api-leilao.jar```

E pronto! A aplicação já estará rodando lindamente.

## 📜 Documentação

```bash
http://localhost:8080/swagger-ui/

# Obs.: Não será necessário qualquer autenticação no profile dev. Caso esteja usando o profile prod por qualquer motivo, lembre-se de gerar o token de autenticação para usar os métodos de requisição DELETE, PUT e POST (o GET é livre).
```

## ✨ Features

- [X] 🗂️ CRUD das entidades
- [X] 💾 Paginação e ordenação de recursos
- [X] 🗃️ Documentação
- [X] 🕸️ Hateoas
