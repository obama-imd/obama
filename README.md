# OBAMA

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API para plataforma Objetos de Aprendizagem para Matemática (OBAMA)

## Pre-requisitos
* [Java] 1.17+;
* [Gradle] 6/7+;

## Arquitetura

Esse projeto segue os conceitos da arquitetura limpa.

## Configurando o ambiente local

### Configurando a base de dados

Inicialmente a base de dados da aplicação pode estar vázia. Caso sim, é importante estrutura-la com o seguinte comando:

```shell
./gradlew flywayMigrate -DServer=local_database
```

### Profile

Para rodar a aplicação localmente é necessário configurar o profile local e para isso basta usar o seguinte trecho:

```
-Dspring.profiles.active=<profile_name>
```

### Rodando a aplicação via gradle

Para rodar a aplicação via terminal basta usar o seguinte comando:

```
./gradlew bootRun
```

Você consegue adicionar o profile como mostra a seguir:

```
./gradlew bootRun --args='--spring.profiles.active=<profile_name>'
```

### Acessar o banco

O banco da aplicação por enquanto está em memória e para acessa-lo basta acessar o seguinte link:

```
http://localhost:8081/h2-console
```

E coloca as seguintes informações:

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:obama;
- User Name: sa
- Password: password

E seleciona o botão Connect

## Swaggers

Swagger está configurado para o link {HOST}/swagger-ui/index.html.

## Jacoco
s
O projeto possui o Jacoco configurado para ser executado após a execução dos testes:

```
./gradlew clean build'
```

Você pode verificar os relatórios de cobertura de teste no caminho `/build/reports/jacoco/html/index.html` do seu projeto.


TODO: Adicionar descrição da necessidade de adicionar uma variável de ambiente chamada JWT_SECRET
