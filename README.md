# OBAMA - Objetos de Aprendizagem para Matemática

![Java](https://img.shields.io/badge/java-21-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-1.9.24-7F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.5.7-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-8.9-02303A.svg?style=for-the-badge&logo=gradle&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-42.7.3-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API REST para plataforma de Objetos de Aprendizagem para Matemática (OBAMA), desenvolvida seguindo os princípios da Arquitetura Limpa (Clean Architecture) e Domain-Driven Design (DDD).

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Arquitetura](#arquitetura)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração e Execução](#configuração-e-execução)
- [Banco de Dados](#banco-de-dados)
- [Documentação da API](#documentação-da-api)
- [Testes](#testes)
- [Monitoramento](#monitoramento)
- [Segurança](#segurança)

## 🎯 Sobre o Projeto

OBAMA é uma plataforma que permite o gerenciamento e busca de objetos de aprendizagem matemáticos, organizados por:
- **Níveis de Ensino**: Educação Infantil, Ensino Fundamental, Ensino Médio
- **Disciplinas**: Matemática e áreas relacionadas
- **Descritores**: Competências e habilidades da BNCC
- **Habilidades**: Habilidades específicas por ano de ensino
- **Temas de Conteúdo**: Temas curriculares organizados por disciplina

A aplicação oferece funcionalidades de:
- Busca e filtragem de objetos de aprendizagem
- Gerenciamento de usuários e autenticação JWT
- Criação e gerenciamento de planos de aula
- Cache de dados frequentes para melhor performance

## 📦 Pré-requisitos

- **Java 21** ou superior
- **Gradle 8.9** ou superior
- **PostgreSQL 12+** (para ambiente de produção)
- **H2 Database** (para ambiente de desenvolvimento/teste - incluído)

### Verificando versões

```bash
java -version  # Deve ser Java 21+
./gradlew --version  # Deve ser Gradle 8.9+
```

## 🏗️ Arquitetura

Este projeto segue os princípios mesclados da **Arquitetura Limpa (Clean Architecture)** e do Domain Driven Desing(DDD), garantindo separação de responsabilidades, testabilidade e independência de frameworks.

### Estrutura de Pacotes

O projeto está organizado em **contextos delimitados** (Bounded Contexts), cada um contendo sua própria estrutura de `domain` e `infrastructure`. Os contextos principais são:

- **`oa`**: Objetos de Aprendizagem
- **`usuario`**: Gerenciamento de Usuários e Autenticação
- **`planoaula`**: Planos de Aula

### Pacote Domain

O pacote `domain` contém as regras de negócio e abstrações do sistema, **sem dependências de frameworks**. Seus sub-pacotes são:

#### 📁 models

Responsáveis por representar conceitos, informações e situações referentes aos negócios além de servirem como objetos de comunicação entre os pacotes `domain` e `infrastructure`.

**Exemplo:**
```kotlin
class ObjetoAprendizagem(
    val id: Long,
    var nome: String,
    var descricao: String,
    var quantidadeAcessos: Int,
    var thumbnailPath: String?,
    val dataLancamento: LocalDate?,
    var versao: String?,
    var ativo: Boolean,
    var tipoLicensaUso: TipoLicensaUso?,
    val idiomas: Set<Idioma>?,
    val autoresMantenedores: Set<AutorMantenedor>,
    val descritores: Set<Descritor>,
    val habilidades: Set<Habilidade>,
    val plataformas: List<ObjetoAprendizagemPlataforma>
) {
    override fun incrementarVersao(other: Any?): Boolean {
       val (major, minor, patch) = versao
          ?.split(".")
          ?.mapNotNull { it.toIntOrNull() }
          ?.takeIf { it.size == 3 }
          ?: listOf(1, 0, 0)

       versao = "$major.$minor.${patch + 1}"
    }
}
```

#### 📁 gateways

Define interfaces de comunicação com recursos externos, como APIs, banco de dados, Kafka, SQS, etc.

**Exemplo:**
```kotlin
interface ObjetoAprendizagemGateway {
    fun procurarPorID(id: Long): ObjetoAprendizagem
    fun procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdAndHabilidadeId(
        pageable: Pageable,
        nome: String?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        tipoAcesso: TipoAcesso?
    ): Page<ObjetoAprendizagem>
}
```

#### 📁 usecase

Representam as regras de fluxo de operações, ou seja, casos de uso do sistema, separados em interfaces e suas respectivas implementações.

**Exemplo - Interface:**
```kotlin
interface ObjetoAprendizagemUseCase {
    fun buscarPorId(id: Long): ObjetoAprendizagem
    fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        tipoAcesso: TipoAcesso?
    ): Page<ObjetoAprendizagem>
}
```

**Exemplo - Implementação:**
```kotlin
class ObjetoAprendizagemUseCaseImpl(
    private val oaGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter
): ObjetoAprendizagemUseCase {
    
    override fun buscarPorId(id: Long): ObjetoAprendizagem {
        logger.info("method={}; id={};", "buscarPorId", id)
        return oaGatewayAdapter.procurarPorID(id)
    }
    
    // ... outros métodos
}
```

#### 📁 exception

Sub-pacote que contém classes para exceções customizadas do domínio.

**Exemplo:**
```kotlin
class OANaoEncontradoException(
    message: String
): RuntimeException(message)
```

#### 📁 enums

Contém enums específicos do domínio.

**Exemplo:**
```kotlin
enum class Curriculo {
    BNCC
}
```

### Pacote Infrastructure

O pacote `infrastructure` contém as implementações e configurações relacionadas ao framework e recursos externos. Seus sub-pacotes são:

#### 📁 adapter

Implementa as interfaces definidas no sub-pacote `gateways` e representam as regras de persistência(dados ou eventos) ou regras de comunicação com aplicações externas.

**Exemplo:**
```kotlin
@Component
class ObjetoAprendizagemDatabaseGatewayAdapter(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): ObjetoAprendizagemGateway {
    
    override fun procurarPorID(id: Long): ObjetoAprendizagem {
        val entity = objetoAprendizagemRepository.buscarPorId(id)
            ?: throw OANaoEncontradoException("Objeto de aprendizagem não encontrado")
        return entity.toDomain()
    }
    
    // ... outros métodos
}
```

#### 📁 resource (Controllers)

Define as rotas REST da aplicação e utiliza os casos de uso do sub-pacote `usecase`.

**Exemplo:**
```kotlin
@RestController
@RequestMapping("/v1/oa")
@Validated
@Tag(
    name = "ObjetoAprendizagemResource",
    description = "Recurso que lida com objetos de aprendizagem"
)
class ObjetoAprendizagemResourceImpl(
    private val objetoAprendizagemUseCase: ObjetoAprendizagemUseCase
): ObjetoAprendizagemResource {
    
    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPorId(
        @PathVariable("id", required = true) id: Long
    ): ResponseEntity<BuscarOaIdResponse> {
        logger.info("method={}; id={};", "buscarPorId", id)
        return ResponseEntity.ok(
            objetoAprendizagemUseCase.buscarPorId(id).toBuscarOaIdResponse()
        )
    }
}
```

#### 📁 exchange (DTOs)

Sub-pacote onde contém classes representativas para corpo de requisições e respostas dos controladores.

**Exemplo:**
```kotlin
data class BuscarOaIdResponse(
    val id: Long,
    val nome: String,
    val descricao: String,
    val quantidadeAcessos: Int,
    val thumbnailPath: String?,
    val dataLancamento: LocalDate?,
    val versao: String?,
    val ativo: Boolean,
    // ... outros campos
)
```

#### 📁 entity

Sub-pacote que contém classes de entidades de banco de dados que representam o negócio da aplicação.

**Exemplo:**
```kotlin
@Entity
@Table(name="objeto_aprendizagem")
class ObjetoAprendizagemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objeto_aprendizagem_gen")
    @SequenceGenerator(name="objeto_aprendizagem_gen", sequenceName = "sq_objeto_aprendizagem_id", allocationSize = 1)
    val id: Long,
    
    @Column(name="nome")
    var nome: String,
    
    @Column(name="descricao", columnDefinition="text")
    var descricao: String,
    
    // ... outros campos
) {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="objeto_aprendizagem_descritor",
        joinColumns = [JoinColumn(name="objeto_aprendizagem_id", referencedColumnName="id")],
        inverseJoinColumns=[JoinColumn(name="descritor_id", referencedColumnName="id")]
    )
    val descritores: MutableSet<DescritorEntity> = hashSetOf()
    
    // ... outros relacionamentos
}
```

#### 📁 repository

Define interfaces ou implementações para a comunicação com base de dados.

**Exemplo:**
```kotlin
@Repository
interface ObjetoAprendizagemRepository: JpaRepository<ObjetoAprendizagemEntity, Long> {
    
    @Query("SELECT oa FROM ObjetoAprendizagemEntity oa WHERE oa.id = :id")
    fun buscarPorId(@Param("id") id: Long): ObjetoAprendizagemEntity?
    
    @Query(
        "SELECT distinct oa FROM ObjetoAprendizagemEntity oa " +
        "LEFT JOIN oa.descritores d " +
        "WHERE (:nome IS NULL OR oa.nome LIKE %:nome%) " +
        "AND oa.ativo = true " +
        "ORDER BY oa.nome"
    )
    fun buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoETemaConteudoEDescritorEHabilidade(
        @Param("nome") nome: String?,
        // ... outros parâmetros
        pageable: Pageable
    ): Page<ObjetoAprendizagemEntity>
}
```

#### 📁 mapper

Contém funções de extensão e objetos para conversão de objetos entre diferentes camadas (Domain ↔ Entity ↔ DTO).

**Exemplo:**
```kotlin
fun ObjetoAprendizagemEntity.toDomain(): ObjetoAprendizagem {
    return ObjetoAprendizagem(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        // ... mapeamento de campos
        descritores = this.descritores.map { it.toDomain() }.toSet()
    )
}

fun ObjetoAprendizagem.toEntity(): ObjetoAprendizagemEntity {
    return ObjetoAprendizagemEntity(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        // ... mapeamento de campos
    )
}

fun ObjetoAprendizagem.toBuscarOaIdResponse(): BuscarOaIdResponse {
    return BuscarOaIdResponse(
        id = this.id,
        nome = this.nome,
        // ... mapeamento de campos
    )
}
```

#### 📁 configuration

Neste sub-pacote contém configurações importantes para o projeto como beans, segurança, cache, etc.

**Exemplo:**
```kotlin
@Configuration
@EnableCaching
class CaffeineConfig {
    
    @Bean
    fun caffeineCacheManager(): CaffeineCacheManager {
        val cacheManager = CaffeineCacheManager(
            "temaconteudos", "niveisensino", "anosensino", "disciplinas"
        )
        cacheManager.setCaffeine(
            Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterAccess(600, TimeUnit.SECONDS)
                .initialCapacity(100)
        )
        return cacheManager
    }
}
```

#### 📁 handler

Contém classes para tratamento global de exceções.

**Exemplo:**
```kotlin
@RestControllerAdvice
class ObjetoAprendizagemExceptionHandler {
    
    @ExceptionHandler(OANaoEncontradoException::class)
    fun handleOANaoEncontrado(
        ex: OANaoEncontradoException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(ex.message ?: "Objeto de aprendizagem não encontrado"))
    }
}
```

## 📂 Estrutura do Projeto

```
obama/
├── src/
│   ├── main/
│   │   ├── kotlin/br/ufrn/imd/obama/
│   │   │   ├── oa/                          # Contexto: Objetos de Aprendizagem
│   │   │   │   ├── domain/
│   │   │   │   │   ├── enums/
│   │   │   │   │   ├── exception/
│   │   │   │   │   ├── gateway/
│   │   │   │   │   ├── model/
│   │   │   │   │   └── usecase/
│   │   │   │   └── infrastructure/
│   │   │   │       ├── adapter/
│   │   │   │       ├── configuration/
│   │   │   │       ├── entity/
│   │   │   │       ├── exception/
│   │   │   │       ├── handler/
│   │   │   │       ├── mapper/
│   │   │   │       ├── repository/
│   │   │   │       └── resource/
│   │   │   ├── usuario/                     # Contexto: Usuários e Autenticação
│   │   │   │   └── [mesma estrutura]
│   │   │   ├── planoaula/                   # Contexto: Planos de Aula
│   │   │   │   └── [mesma estrutura]
│   │   │   └── ObamaApplication.kt
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-local.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       └── db/migration/                # Flyway migrations
│   └── test/
│       └── kotlin/br/ufrn/imd/obama/
│           └── [estrutura similar para testes]
├── build.gradle
├── settings.gradle.kts
├── gradle/
│   └── wrapper/
├── docker-compose.yml
├── Dockerfile
└── README.md
```

## 🛠️ Tecnologias Utilizadas

### Core
- **Java 21**: Linguagem base da JVM
- **Kotlin 1.9.24**: Linguagem principal de desenvolvimento
- **Spring Boot 3.5.7**: Framework principal
- **Gradle 8.9**: Gerenciador de dependências e build

### Persistência
- **Spring Data JPA**: Abstração para acesso a dados
- **Hibernate**: ORM
- **PostgreSQL 42.7.3**: Banco de dados de produção
- **H2 2.2.224**: Banco de dados em memória para testes
- **Flyway**: Migração de banco de dados

### Segurança
- **Spring Security**: Framework de segurança
- **JWT (Auth0)**: Autenticação via tokens
- **BCrypt**: Criptografia de senhas

### Documentação e API
- **SpringDoc OpenAPI 2.8.14**: Documentação Swagger/OpenAPI
- **Spring REST Docs**: Documentação de API

### Cache
- **Caffeine 3.1.8**: Cache em memória de alta performance

### Testes
- **JUnit 5**: Framework de testes
- **Mockito**: Mocking framework
- **Testcontainers**: Testes de integração com containers
- **JaCoCo**: Cobertura de código

### Outros
- **Spring Actuator**: Monitoramento e métricas
- **Spring Mail**: Envio de emails
- **Lombok**: Redução de boilerplate

## ⚙️ Configuração e Execução

### 1. Configurar Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto ou configure as variáveis de ambiente:

```bash
# JWT Secret (obrigatório)
JWT_SECRET=seu_secret_jwt_aqui

# Senha do Email (opcional, para envio de emails)
SENHA_APP_EMAIL=sua_senha_email_aqui

# Banco de Dados (para produção)
DB_URL=jdbc:postgresql://localhost:5432/obama
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

### 2. Executar Localmente

#### Via Gradle

```bash
# Executar com perfil local
./gradlew bootRun --args='--spring.profiles.active=local' -DJWT_SECRET=seu_secret

# Ou configurar variáveis de ambiente
export JWT_SECRET=seu_secret
./gradlew bootRun --args='--spring.profiles.active=local'
```

#### Via IntelliJ IDEA

1. Abra o projeto no IntelliJ
2. Vá em `Run > Edit Configurations`
3. Configure:
   - **Main class**: `br.ufrn.imd.obama.ObamaApplicationKt`
   - **VM options**: `-Dspring.profiles.active=local`
   - **Environment variables**: `JWT_SECRET=seu_secret`

### 3. Executar via Docker

```bash
# Criar arquivo .env com as variáveis necessárias
cp .env.example .env
# Editar .env com seus valores

# Executar com docker-compose
docker-compose up -d

# Ou com docker stack
docker stack deploy -c docker-compose.yml obama
```

### 4. Build do Projeto

```bash
# Build completo com testes
./gradlew clean build

# Build sem testes
./gradlew clean build -x test

# Apenas compilar
./gradlew compileKotlin
```

## 🗄️ Banco de Dados

### Migrações (Flyway)

As migrações do banco de dados estão em `src/main/resources/db/migration/` e são executadas automaticamente na inicialização da aplicação.

Para executar migrações manualmente:

```bash
./gradlew flywayMigrate -Dspring.profiles.active=local
```

### Acessar H2 Console (Ambiente Local)

1. Acesse: `http://localhost:8081/h2-console`
2. Configure:
   - **Driver Class**: `org.h2.Driver`
   - **JDBC URL**: `jdbc:h2:mem:obama;MODE=PostgreSQL`
   - **User Name**: `sa`
   - **Password**: `password`

### Estrutura Principal

- **objeto_aprendizagem**: Objetos de aprendizagem
- **descritor**: Descritores da BNCC
- **habilidade**: Habilidades por ano de ensino
- **nivel_ensino**: Níveis de ensino
- **ano_ensino**: Anos de ensino
- **disciplina**: Disciplinas
- **tema_conteudo**: Temas de conteúdo
- **usuario**: Usuários do sistema
- **plano_aula**: Planos de aula

## 📚 Documentação da API

### Swagger UI

A documentação interativa da API está disponível em:

```
http://localhost:8081/swagger-ui/index.html
```

## 🧪 Testes

### Executar Testes

```bash
# Todos os testes
./gradlew test

# Testes específicos
./gradlew test --tests "ObjetoAprendizagemResourceImplTest"

# Com relatório de cobertura
./gradlew clean test jacocoTestReport
```

### Cobertura de Código (JaCoCo)

Após executar os testes, o relatório de cobertura está disponível em:

```
build/reports/jacoco/test/html/index.html
```

### Configuração de Testes

- **Perfil**: `test`
- **Banco**: H2 em memória
- **Mockito**: Configurado para Java 21 com `mock-maker-inline`

## 📊 Monitoramento

### Spring Actuator

Endpoints de monitoramento disponíveis:

- **Health Check**: `http://localhost:8081/actuator/health`
- **Info**: `http://localhost:8081/actuator/info`
- **Metrics**: `http://localhost:8081/actuator/metrics`

## 🔒 Segurança

### Autenticação JWT

A aplicação utiliza JWT (JSON Web Tokens) para autenticação:

- **Access Token**: Expira em 5 minutos (configurável)
- **Refresh Token**: Expira em 60 minutos (configurável)
- **Algoritmo**: HS256

### Critérios de Senha

As senhas devem atender aos seguintes critérios:
- Mínimo de 8 caracteres
- Pelo menos uma letra maiúscula
- Pelo menos uma letra minúscula
- Pelo menos um número
- Pelo menos um caractere especial (@, #, $, etc.)

### Configuração de Segurança

A configuração de segurança está em:
- `usuario/infrastructure/configuration/SecurityConfiguration.kt`

## 🚀 Deploy

### Build para Produção

```bash
# Gerar JAR
./gradlew clean build -x test

# O JAR estará em: build/libs/obama-0.0.1-SNAPSHOT.jar
```

### Executar JAR

```bash
java -jar build/libs/obama-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=prod \
  -DJWT_SECRET=seu_secret
```

## 📝 Perfis de Execução

- **local**: Ambiente local com H2 em memória
- **dev**: Ambiente de desenvolvimento
- **prod**: Ambiente de produção
- **test**: Ambiente de testes

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença especificada no arquivo `LICENSE.md`.

## 📞 Suporte

Para suporte, envie um email para obama@imd.ufrn.br ou abra uma issue no repositório.

---

**Desenvolvido com ❤️ usando Spring Boot, Kotlin e Arquitetura Limpa**
