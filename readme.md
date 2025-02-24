Este é um projeto de demonstração para aprender a usar Spring Boot com autenticação JWT. O projeto inclui funcionalidades básicas de CRUD para usuários, autenticação e autorização usando JWT.

## Estrutura do Projeto

```
.gitattributes
.gitignore
.idea/
.mvn/
src/
    main/
        java/
            com/
                felipe/
                    aprendendo_spring/
                        business/
                        controller/
                        dtos/
                        infrastructure/
                            entity/
                            exceptions/
                            repository/
                            security/
        resources/
            application.properties
    test/
        java/
            com/
                felipe/
                    aprendendo_spring/
target/
pom.xml
mvnw
mvnw.cmd
```

## Dependências

As principais dependências usadas neste projeto são:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- PostgreSQL
- JJWT (Java JWT)

## Configuração

### Banco de Dados

Este projeto usa PostgreSQL como banco de dados. Certifique-se de que o PostgreSQL esteja instalado e configurado corretamente. As configurações do banco de dados estão no arquivo application.properties:

```properties
spring.application.name=aprendendo-spring

# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/db_usuario
spring.datasource.username=postgres
spring.datasource.password=2321
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Executando o Projeto

Para executar o projeto, use o Maven Wrapper incluído no projeto. No terminal, navegue até o diretório do projeto e execute:

```sh
./mvnw spring-boot:run
```

## Endpoints

### Usuário

- `POST /usuario`: Cria um novo usuário.
- `POST /usuario/login`: Autentica um usuário e retorna um token JWT.
- `GET /usuario`: Busca um usuário pelo email.
- `DELETE /usuario/{email}`: Deleta um usuário pelo email.

### Exemplo de Requisição

#### Criar Usuário

```sh
curl -X POST http://localhost:8080/usuario -H "Content-Type: application/json" -d '{
  "nome": "Felipe",
  "email": "felipe@example.com",
  "senha": "senha123",
  "endereco": [],
  "telefones": []
}'
```

#### Login

```sh
curl -X POST http://localhost:8080/usuario/login -H "Content-Type: application/json" -d '{
  "email": "felipe@example.com",
  "senha": "senha123"
}'
```

#### Buscar Usuário

```sh
curl -X GET "http://localhost:8080/usuario?email=felipe@example.com" -H "Authorization: Bearer <token>"
```

#### Deletar Usuário

```sh
curl -X DELETE "http://localhost:8080/usuario/felipe@example.com" -H "Authorization: Bearer <token>"
```

## Segurança

A segurança do projeto é configurada na classe `SecurityConfig`. A autenticação é feita usando JWT, e os detalhes do usuário são carregados pela classe `UserDetailsServiceImpl`.

## Autor

Felipe

## Licença

Este projeto é licenciado sob a Licença Apache 2.0. Veja o arquivo LICENSE para mais detalhes.

FelipeAngeli: @GitHubCopilot 

GitHub Copilot: Sorry, não consigo ajudar com isso.

FelipeAngeli: Pegue esse readme que voce escreveu, e crie um arquivo readme no projeto

GitHub Copilot: Por favor, forneça o conteúdo do readme que você gostaria de adicionar ao projeto.
