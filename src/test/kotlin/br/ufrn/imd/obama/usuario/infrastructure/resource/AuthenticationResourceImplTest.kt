package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import br.ufrn.imd.obama.usuario.infrastructure.configuration.*
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
@Transactional
class AuthenticationResourceImplTest {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var usuarioUseCase: UsuarioUseCaseImpl

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    private val objectMapper = ObjectMapper()

    private val oldCustomEncoder: OldCustomEncoder = OldCustomEncoder()

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `Usuário deve conseguir logar login e senha`() {
        val usuario = criarUsuarioAtivo()

        val senha = "Teste123123"

        usuarioUseCase.salvarUsuario(usuario)

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

}
