# Wishlist

## Stack
- Java 17
- Maven
- MongoDB

## Iniciando o serviço
### Pré-requisitos
- GitHub
- Docker
- Maven

```sh
# Clonando o projeto
$ git clone https://github.com/robertorsm/wishlist.git
```

## Build
```sh
# Realizar o build local
$ mvn clean install
```

## Execução
 - Dentro da pasta docker-local tem um `compose.yml` que foi usado para subir o mongoDB, para fins de agilidade não foram configuradas credenciais para o database.
 - Depois da instância do mongo rodando basta rodar a aplicação em alguma IDE de preferência ou rodar o jar diretamente, para isso após o `mvn clean install` basta rodar o comando abaixo a partir da raiz do projeto:

```sh
# Rodando a partir do jar
$ java -jar target/wishlist-0.0.1-SNAPSHOT.jar
```

## Acessando a documentação da API
- Após a aplicação estiver executando o Swagger estará disponível na porta `http://localhost:8080/swagger-ui/index.html`.

 
