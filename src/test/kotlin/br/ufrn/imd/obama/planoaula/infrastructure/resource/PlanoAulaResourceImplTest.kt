package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarAnoEnsino
import br.ufrn.imd.obama.oa.util.criarDisciplina
import br.ufrn.imd.obama.oa.util.criarNivelEnsino
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.planoaula.util.criarPlanoAula
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginResponse
import br.ufrn.imd.obama.usuario.util.EMAIL1
import br.ufrn.imd.obama.usuario.util.EMAIL2
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@Import(
    value =
    [
        TokenConfiguration::class,
        SecurityConfiguration::class
    ]
)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class PlanoAulaResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var planoAulaUseCase: PlanoAulaUseCase

    @Autowired
    private lateinit var planoAulaRepository: PlanoAulaRepository

    private val objectMapper = ObjectMapper()

    companion object {
        const val pageSize = 10
        const val titulo = "teste"
    }

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var oaRepository: ObjetoAprendizagemRepository

    @Autowired
    private lateinit var nivelEnsinoRepository: NivelEnsinoRepository

    @Autowired
    private lateinit var anoEnsinoRepository: AnoEnsinoRepository

    @Autowired
    private lateinit var disciplinaRepository: DisciplinaRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun pegarAccessToken(): String {
        val usuario = criarUsuarioAtivo()

        val senha = usuario.senha

        usuario.senha = passwordEncoder.encode(usuario.senha)
        usuarioRepository.save(usuario.toEntity())

        val request = criarLoginRequest(usuario.email, senha)

        val loginRequestJson = objectMapper.writeValueAsString(request)

        val resultado = mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson)
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andReturn()

        val jsonResponse = resultado.response.contentAsString
        return objectMapper.readValue(jsonResponse, LoginResponse::class.java).accessToken
    }

    @Test
    @DirtiesContext
    fun `Deve retornar 200 ao passar parametros corretos para listar planos de aula`() {
        val token = "Bearer ${pegarAccessToken()}"

        mockMvc.perform(
            get("/v1/planoaula")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", titulo)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isOk())
    }

    @Test
    @DirtiesContext
    fun `Deve retornar 200 ao passar titulo como nulo`() {
        val token = "Bearer ${pegarAccessToken()}"

        mockMvc.perform(
            get("/v1/planoaula")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", null)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isOk())
    }

    @Test
    fun `Deve retornar 403 quando o usuario nao estiver autorizado`() {
        mockMvc.perform(
            get("/v1/planoaula")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", titulo)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    @DirtiesContext
    fun `Deve retornar 200 ao buscar planos por coautor e nao houver`() {
        val token = "Bearer ${pegarAccessToken()}";

        mockMvc.perform(
            get("/v1/planoaula/coautor")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isEmpty)
    }

    @Test
    fun `Deve retornar 200 ao buscar planos por coautor e houver`() {
        val token = "Bearer ${pegarAccessToken()}"
        val coautor = criarUsuarioAtivo(1L, EMAIL1)
        val autor = criarUsuarioAtivo(2L, EMAIL2)

        usuarioRepository.save(autor.toEntity())
        oaRepository.save(criarObjetoAprendizagem().toEntity())

        planoAulaRepository.save(
            criarPlanoAula(
                1L,
                autor = autor,
                nivelEnsino = criarNivelEnsino(),
                disciplinas = listOf(criarDisciplina()),
                anoEnsino = criarAnoEnsino(),
                objetosAprendizagem = setOf(criarObjetoAprendizagem()),
                coautores = setOf(coautor)
            ).toEntity()
        );

        planoAulaRepository.save(
            criarPlanoAula(
                2L,
                titulo = titulo + "2",
                autor = autor,
                nivelEnsino = criarNivelEnsino(),
                disciplinas = listOf(criarDisciplina()),
                anoEnsino = criarAnoEnsino(),
                objetosAprendizagem = setOf(criarObjetoAprendizagem()),
                coautores = setOf(coautor)
            ).toEntity())

        mockMvc.perform(
            get("/v1/planoaula/coautor")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isNotEmpty)
    }

    @Test
    fun `Deve retornar 200 ao buscar planos por coautor e filtrar por titulo`() {
        val token = "Bearer ${pegarAccessToken()}"
        val coautor = criarUsuarioAtivo(3L, EMAIL1)
        val autor = criarUsuarioAtivo(4L, EMAIL2)

        usuarioRepository.save(autor.toEntity())
        oaRepository.save(criarObjetoAprendizagem().toEntity())

        planoAulaRepository.save(
            criarPlanoAula(
                1L,
                autor = autor,
                nivelEnsino = criarNivelEnsino(),
                disciplinas = listOf(criarDisciplina()),
                anoEnsino = criarAnoEnsino(),
                objetosAprendizagem = setOf(criarObjetoAprendizagem()),
                coautores = setOf(coautor)
            ).toEntity()
        );

        planoAulaRepository.save(
            criarPlanoAula(
                2L,
                titulo = titulo + "2",
                autor = autor,
                nivelEnsino = criarNivelEnsino(),
                disciplinas = listOf(criarDisciplina()),
                anoEnsino = criarAnoEnsino(),
                objetosAprendizagem = setOf(criarObjetoAprendizagem()),
                coautores = setOf(coautor)
            ).toEntity())

        mockMvc.perform(
            get("/v1/planoaula/coautor")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("titulo", titulo + "2")
        )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").isNotEmpty)
            .andExpect(jsonPath("$.numberOfElements").value(1))
    }

    @Test
    fun `Deve retornar 403 ao buscar planos por coautor quando usuario nao estiver autorizado`() {
        mockMvc.perform(
            get("/v1/planoaula/buscarPorCoautor")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", titulo)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    fun `Deve retornar 201 ao salvar plano de aula com sucesso`() {
        val token = "Bearer ${pegarAccessToken()}"

        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        val anoEnsino = anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))
        val disciplina2 = disciplinaRepository.save(DisciplinaEntity(id = 2L, nome = "Português"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to nivelEnsino.id,
            "disciplinas_envolvidas" to listOf(disciplina1.id, disciplina2.id),
            "id_ano_ensino" to anoEnsino.id,
            "duracao_em_minutos" to 60,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    fun `Deve retornar 403 quanto o usuario não estiver autenticado`() {
        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        val anoEnsino = anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))
        val disciplina2 = disciplinaRepository.save(DisciplinaEntity(id = 2L, nome = "Português"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to nivelEnsino.id,
            "disciplinas_envolvidas" to listOf(disciplina1.id, disciplina2.id),
            "id_ano_ensino" to anoEnsino.id,
            "duracao_em_minutos" to 60,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    fun `Deve retornar 400 quando id do nivel ensino for invalido`() {
        val token = "Bearer ${pegarAccessToken()}"

        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        val anoEnsino = anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))
        val disciplina2 = disciplinaRepository.save(DisciplinaEntity(id = 2L, nome = "Português"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to 999L,
            "disciplinas_envolvidas" to listOf(disciplina1.id, disciplina2.id),
            "id_ano_ensino" to anoEnsino.id,
            "duracao_em_minutos" to 60,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `Deve retornar 400 quando algum id da lista de ids das disciplinas envolvidas for invalido`() {
        val token = "Bearer ${pegarAccessToken()}"

        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        val anoEnsino = anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to nivelEnsino.id,
            "disciplinas_envolvidas" to listOf(disciplina1.id, 999L),
            "id_ano_ensino" to anoEnsino.id,
            "duracao_em_minutos" to 60,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `Deve retornar 400 quando id do ano ensino for invalido`() {
        val token = "Bearer ${pegarAccessToken()}"

        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to nivelEnsino.id,
            "disciplinas_envolvidas" to listOf(disciplina1.id),
            "id_ano_ensino" to 999L,
            "duracao_em_minutos" to 60,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `Deve retornar 400 quando duracao em minutos for menor do que 0`() {
        val token = "Bearer ${pegarAccessToken()}"

        val nivelEnsino =
            nivelEnsinoRepository.save(NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF"))
        val anoEnsino = anoEnsinoRepository.save(AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino))
        val disciplina1 = disciplinaRepository.save(DisciplinaEntity(id = 1L, nome = "Matemática"))
        val disciplina2 = disciplinaRepository.save(DisciplinaEntity(id = 2L, nome = "Português"))

        val requestBody = mapOf(
            "escola" to "Escola Teste",
            "id_nivel_ensino" to nivelEnsino.id,
            "disciplinas_envolvidas" to listOf(disciplina1.id, disciplina2.id),
            "id_ano_ensino" to anoEnsino.id,
            "duracao_em_minutos" to -10,
            "titulo" to "Título Teste",
            "metodologia" to "Metodologia Teste",
            "objetivos_especificos" to "Objetivos Específicos Teste",
            "objetivo_geral" to "Objetivo Geral Teste",
            "avaliacao" to "Avaliação Teste",
            "referencias" to "Referências Teste"
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/salvar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `Nao deve associar OA a plano aula quando plano nao existir`() {
        val token = "Bearer ${pegarAccessToken()}"

        val requestBody = mapOf(
            "plano_id" to "1",
            "objeto_aprendizagem_ids" to setOf(1)
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/associar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isNotFound())
    }

    @Test
    fun `Nao deve associar OA a plano aula quando id de OA for invalido`() {
        val token = "Bearer ${pegarAccessToken()}"

        val requestBody = mapOf(
            "plano_id" to "1",
            "objeto_aprendizagem_ids" to setOf(1)
        )

        val requestJson = objectMapper.writeValueAsString(requestBody)

        mockMvc.perform(
            post("/v1/planoaula/associar")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isNotFound())
    }

    private fun criarLoginRequest(email: String, senha: String): LoginRequest {
        return LoginRequest(
            senha = senha,
            login = email
        )
    }
}
