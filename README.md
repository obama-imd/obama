# OBAMA
API para plataforma Objetos de Aprendizagem para Matemática (OBAMA)

## Pre-requisitos
* [Java] 1.17+;
* [Gradle] 6/7+;

## Arquitetura

Esse projeto segue os conceitos da arquitetura limpa.

## Configurando os containers locais

O projeto possui arquivo docker-compose permite você configura os containers essenciais para rodar a aplicação local numa rede. No Linux, para rodar esse arquivo basta executar o seguinte comando:

```shell
docker-compose up --build -d
```

**Subindo alguns serviços**

```shell
docker-compose up [ servico_01 | servico_02] --build -d
```

**Parar todos os containers**

```shell
docker-compose down
```

**Outros comandos**

Para deletar images/containers/volumes/network não utilizados.

```shell
docker system prune -f && docker network prune -f && docker image prune -a -f && docker volume prune -f
```

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

### Rodando a aplicação via docker

É possível rodar a aplicação executando a construção da imagem pelo Dockerfile do projeto e depois executando essa.

Para isso rode este comando para construir a imagem:

```
docker build -t obama .
```

Caso queira ver se a imagem for criado execute este comando:

```
docker images
```

Agora para executar (criar um container da imagem criada), execute esse comando:

```
docker run --net=host -p '8080:8080' -e SPRING_PROFILES_ACTIVE=local obama
```

## Swaggers

Swagger está configurado para o link {HOST}/swagger-ui/index.html.

## Jacoco
s
O projeto possui o Jacoco configurado para ser executado após a execução dos testes:

```
./gradlew clean build'
```

Você pode verificar os relatórios de cobertura de teste no caminho `/build/reports/jacoco/html/index.html` do seu projeto.
