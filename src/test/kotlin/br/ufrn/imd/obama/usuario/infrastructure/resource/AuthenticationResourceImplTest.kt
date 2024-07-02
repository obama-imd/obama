package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityFilter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenService
import br.ufrn.imd.obama.usuario.infrastructure.configuration.UsuarioConfig
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = [SecurityConfiguration::class, SecurityFilter::class, TokenService::class, UsuarioConfig::class])
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class AuthenticationResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var usuarioUseCase: UsuarioUseCaseImpl


    private val objectMapper = ObjectMapper()

    @Test
    fun `Usuário deve conseguir logar login e senha`() {
        val usuario = criarUsuarioAtivo()

        val senha = usuario.senha

        usuarioUseCase.salvarUsuario(usuario)

        usuario.senha = senha

        val request = criarLoginRequest(usuario)

        val loginRequestJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson)
        )
            .andDo(print())
            .andExpect(status().isOk)
    }


    private fun criarLoginRequest(usuario: Usuario): LoginRequest {
        return LoginRequest(
            senha = usuario.senha,
            login = usuario.email
        )
    }

}
