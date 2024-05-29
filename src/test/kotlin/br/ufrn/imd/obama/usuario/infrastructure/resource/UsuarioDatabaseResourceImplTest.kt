package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioDatabaseUseCase
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityFilter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenService
import br.ufrn.imd.obama.usuario.infrastructure.configuration.UsuarioConfig
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.util.criarUsuario
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(
    classes = [
        UsuarioConfig::class,
        UsuarioDatabaseGatewayAdapter::class,
        UsuarioDatabaseUseCase::class,
        UsuarioRepository::class,
        UsuarioDatabaseResourceImpl::class,
        SecurityConfiguration::class,
        SecurityFilter::class,
        TokenService::class,
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class UsuarioDatabaseResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var usuarioDatabaseUseCase: UsuarioDatabaseUseCase

    @MockBean
    private lateinit var usuarioDatabaseGatewayAdapter: UsuarioDatabaseGatewayAdapter

    @MockBean
    private lateinit var usuarioRepository: UsuarioRepository

    @Test
    fun `Deve retornar ok quando salvar um usuário com dados corretos`() {
        val usuario = criarUsuario()

        Mockito.`when`(
            usuarioDatabaseUseCase.salvarUsuario(
                nome = usuario.nome,
                sobrenome = usuario.sobrenome,
                email = usuario.email,
                senha = usuario.senha
            )
        ).thenReturn(usuario)

        val usuarioJson = ObjectMapper().writeValueAsString(usuario)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/usuario/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
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
