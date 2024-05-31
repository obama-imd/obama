package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class UsuarioResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var usuarioDatabaseUseCase: UsuarioUseCase

    private val objectMapper = ObjectMapper()

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
            "teste"
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

    private fun criarUsuarioRequest(usuario: Usuario): CadastrarUsuarioRequest {
        return CadastrarUsuarioRequest(
            nome = usuario.nome,
            senha = usuario.senha,
            email = usuario.email,
            sobrenome = usuario.sobrenome
        )
    }

}
