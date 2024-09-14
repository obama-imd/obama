package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginResponse
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@Import(value=
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

    companion object{
        const val pageSize = 10
        const val titulo = "teste"
    }

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private fun criarLoginRequest(email: String, senha: String): LoginRequest {
        return LoginRequest(
            senha = senha,
            login = email
        )
    }

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
}
