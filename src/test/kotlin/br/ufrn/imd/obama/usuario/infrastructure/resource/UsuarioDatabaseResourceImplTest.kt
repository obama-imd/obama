package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class UsuarioDatabaseResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var usuarioDatabaseUseCase: UsuarioUseCase

    private val objectMapper = ObjectMapper()

    @Test
    fun `Deve retornar ok quando salvar um usuário com dados corretos`() {
        val usuario = criarUsuarioInativo()

        val request = criarUsuarioRequest(usuario)

        val usuarioJson = objectMapper.writeValueAsString(request)

        mockMvc.perform(
                post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(print())
            .andExpect(status().isOk())
    }

    private fun criarUsuarioRequest(usuario: Usuario): CadastrarUsuarioRequest {
        return CadastrarUsuarioRequest(
            nome = usuario.nome,
            senha = usuario.senha,
            email = usuario.email,
            sobrenome = usuario.sobrenome
        )
    }

    //Todo: Fazer teste caso o e-mail seja um e-mail inválido pela anotação @Email

    //Todo: Fazer teste caso a senha informada seja menor do que 8 caracteres pela anotação @Senha

//    @Test
//    fun `Deve retornar erro quando a criação do usuário falha`() {
//        val usuario = criarUsuario()
//
//        Mockito.`when`(
//            usuarioDatabaseUseCase.salvarUsuario(usuario)
//        ).thenThrow(RuntimeException("Erro ao criar usuário"))
//
//        val usuarioJson = ObjectMapper().writeValueAsString(usuario)
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/v1/usuario/cadastrar")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(usuarioJson)
//        )
//            .andDo(MockMvcResultHandlers.print())
//            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
//    }
}
