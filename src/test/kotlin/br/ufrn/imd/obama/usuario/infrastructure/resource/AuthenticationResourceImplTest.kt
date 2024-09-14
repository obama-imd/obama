package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.infrastructure.configuration.OldCustomEncoder
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
class AuthenticationResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    private val objectMapper = ObjectMapper()

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private val oldCustomEncoder: OldCustomEncoder = OldCustomEncoder()

    @Test
    @DirtiesContext
    fun `Usuário deve conseguir logar login e senha`() {
        val usuario = criarUsuarioAtivo()

        val senha = usuario.senha

        usuario.senha = passwordEncoder.encode(usuario.senha)
        usuarioRepository.save(usuario.toEntity())

        val request = criarLoginRequest(usuario.email, senha)

        val loginRequestJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    private fun criarLoginRequest(email: String, senha: String): LoginRequest {
        return LoginRequest(
            senha = senha,
            login = email
        )
    }


    @Test
    @DirtiesContext
    fun `Usuário com criptografia antiga deve conseguir logar login e senha`() {
        val senha = "Teste123123"

        val senhaCriptografada = oldCustomEncoder.encode(senha)

        val usuarioSalvo = criarUsuarioAtivo()
        usuarioSalvo.senha = senhaCriptografada
        usuarioSalvo.usaCriptografiaAntiga = true

        usuarioRepository.save(usuarioSalvo.toEntity())

        val request = criarLoginRequest(usuarioSalvo.email, senha)

        val loginRequestJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    @DirtiesContext
    fun `Usuario deve conseguir gerar uma token e atualiza-lo através do refreshToken`() {
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
        val token = objectMapper.readValue(jsonResponse, LoginResponse::class.java).refreshToken
        val bearerToken = "Bearer $token"

        mockMvc.perform(
            post("/v1/auth/refresh-token")
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

}
