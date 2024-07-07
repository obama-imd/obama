# OBAMA

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API para plataforma Objetos de Aprendizagem para Matemática (OBAMA)

## Tabela de contéudo

- [Pre-requisitos](#pre-requisitos)
- [Arquitetura](#arquitetura)
- [Uso](#uso)
- [Banco de Dados](#banco-de-dados)
- [Swagger](#swagger)
- [Jacoco](#jacoco)
- [Saúde da Aplicação](#saúde-da-aplicação)

## Pre-requisitos
* [Java] 1.17+;
* [Gradle] 6/7+;

## Arquitetura

Esse projeto segue os conceitos da arquitetura limpa.

## Uso

### Configurar perfil de execução

Para rodar a aplicação é necessário declarar o perfil de execução e para isso basta usar o seguinte trecho:

```
-Dspring.profiles.active=<nome_profile>
```

OBS: Substitua <nome_profile> pelo nome do perfil da aplicação que deseja utilizar. Caso seja local use a palavra `local`.

### Configurar segredo JWT

Para rodar a aplicação é necessário declara qual segredo a aplicação vai utilizar para gerar a criptografia das senhas do usuário. Esse valor é uma variável de ambiente que precisa ser declarada da seguinte maneira:

```
JWT_SECRET=<segredo>
```

OBS: Substitua <segredo> pelo valor do segredo que queira. Por exemplo, `segredo`.

Essa variável pode ser declarada na configuração de execução do projeto pelo IntelliJ. Vá em `Edit Configurations > Environment variables`.

### Inicializar a aplicação via gradle

Para rodar a aplicação via terminal basta usar o seguinte comando:

```
./gradlew bootRun --args='--spring.profiles.active=<profile_name>' -DJWT_SECRET=<segredo>
```

## Banco de dados

### Configurar base de dados

A aplicação tem um base de dados em memória que simula um banco PostgreSQL. Essa base  pode não ter executado as migrações com sucesso e estar vázia. Caso sim, é importante estrutura-la com o seguinte comando:

```shell
./gradlew flywayMigrate -DServer=local_database
```

### Acessar o banco local

O banco da aplicação por enquanto está em memória e para acessa-lo basta acessar o seguinte link:

```
http://localhost:8081/h2-console
```

E colocar as seguintes informações:

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:mem:obama;
- User Name: sa
- Password: password

E seleciona o botão Connect

## Swagger

Swagger está configurado para o link `{HOST}/swagger-ui/index.html`.

## Jacoco

O projeto possui o Jacoco configurado para ser executado após a execução dos testes:

```
./gradlew clean build
```

Você pode verificar os relatórios de cobertura de teste no caminho `/build/reports/jacoco/html/index.html` do seu projeto.


TODO: Adicionar descrição da necessidade de adicionar uma variável de ambiente chamada JWT_SECRET

## Saúde da aplicação

Para verificar a saúde da aplicação basta acessar `{HOST}/actuator/health` no navegador.

## Critérios de segurnaça

### Senha

- Pelo menos 8 caracteres.
- Pelo menos uma letra maiúscula.
- Pelo menos uma letra minúscula.
- Conter pelo menos um número.
- Conter pelo menos um caractere especial (por exemplo, @, #, $, etc.).
