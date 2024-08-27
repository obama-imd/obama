package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.AtivarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.util.criaUsuarioSenhaInvalida
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

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
class UsuarioResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var usuarioDatabaseUseCase: UsuarioUseCase

    @Autowired
    private lateinit var usarioRepository: UsuarioRepository

    private val objectMapper = ObjectMapper()

    val tokenTeste = UUID.randomUUID().toString()

    @Test
    fun `Deve retornar 201 created quando salvar um usuário com dados corretos`() {
        val usuario = criarUsuarioInativo()

        val request = criarUsuarioRequest(usuario)

        val usuarioJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    fun `Deve retornar erro 400 quando o e-mail for inválido`() {
        val request = CadastrarUsuarioRequest(
            nome = "Teste",
            sobrenome = "Teste",
            email = "email", // E-mail inválido
            senha = "Teste12346"
        )
        val usuarioJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `Deve retornar erro 400 quando a senha tiver menos de 8 caracteres`() {
        val usuario =  Usuario(
            "Teste",
            "Teste",
            "emailInvalido",
            "sen",
            Papel.PADRAO,
            false,
            TipoCadastro.PADRAO,
            tokenTeste
        )
        val request = criarUsuarioRequest(usuario)
        val usuarioJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `deve retornar 204 No Content quando usuario não esta ativado e deve ativar o usuario`() {

        val usuarioInativo = Usuario(
            "Teste",
            "Teste",
            "emailInvalido",
            "sen",
            Papel.PADRAO,
            false,
            TipoCadastro.PADRAO,
            tokenTeste
        )
        usarioRepository.save(usuarioInativo.toEntity())

        mockMvc.perform(
            patch("/v1/usuario/ativar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AtivarUsuarioRequest(tokenTeste)))
        )
            .andDo(print())
            .andExpect(status().isNoContent)
    }

    @Test
    fun `deve retornar 204 No Content quando usuario já esta ativado`() {

        val usuarioAtivo = Usuario(
            "Teste",
            "Teste",
            "emailInvalido",
            "sen",
            Papel.PADRAO,
            true,
            TipoCadastro.PADRAO,
            tokenTeste
        )
        usarioRepository.save(usuarioAtivo.toEntity())

        val request = criarAtivarUsuarioRequest(tokenTeste)
        val requestJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            patch("/v1/usuario/ativar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
            .andDo(print())
            .andExpect(status().isNoContent)
    }

    @Test
    fun `deve retornar 204 quando usario não é encontrado por token`() {
        val token = "invalidToken"

        mockMvc.perform(
            patch("/v1/usuario/ativar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AtivarUsuarioRequest(token)))
        )
            .andDo(print())
            .andExpect(status().isNoContent)
    }

    @Test
    fun `deve cadastrar, ativar e fazer login corretamente`() {
        val usuario = criarUsuarioInativo()

        val mvcResult = mockMvc.perform(
            post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criarUsuarioRequest(usuario)))
        )
            .andDo(print())
            .andExpect(status().isCreated)

        //O response não retorna o token
        val usuarioCriado = usarioRepository.findByEmail(usuario.email)

        mockMvc.perform(
            patch("/v1/usuario/ativar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AtivarUsuarioRequest(usuarioCriado!!.token)))
        )
            .andDo(print())
            .andExpect(status().isNoContent)

        val loginRequest = LoginRequest(
            login = usuario.email,
            senha = usuario.senha
        )

        mockMvc.perform(
            post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        )
            .andDo(print())
            .andExpect(status().isOk)
    }

    private fun criarUsuarioRequest(usuario: Usuario): CadastrarUsuarioRequest {
        return CadastrarUsuarioRequest(
            nome = usuario.nome,
            senha = usuario.senha,
            email = usuario.email,
            sobrenome = usuario.sobrenome
        )
    }

    private fun criarAtivarUsuarioRequest(token: String): AtivarUsuarioRequest {
        return AtivarUsuarioRequest(token)
    }

    @Test
    fun `Não deve retornar 201 created quando salvar um usuário com senha inválida`() {
        val usuario = criaUsuarioSenhaInvalida()

        val request = criarUsuarioRequest(usuario)

        val usuarioJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
            post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)
    }

}
