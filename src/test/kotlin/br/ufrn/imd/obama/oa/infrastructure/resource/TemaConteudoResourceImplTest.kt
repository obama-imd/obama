package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.TemaConteudoConfig
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import br.ufrn.imd.obama.oa.util.criarTemaConteudoBNCC
import br.ufrn.imd.obama.oa.util.criarTemaConteudoPCN
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityFilter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenService
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
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
        TemaConteudoConfig::class,
        TemaConteudoUseCase::class,
        TemaConteudoDatabaseGatewayAdapter::class,
        TemaConteudoRepository::class,
        TemaConteudoResourceImpl::class,
        SecurityConfiguration::class,
        SecurityFilter::class,
        TokenService::class,
        UsuarioDatabaseGatewayAdapter::class,
        UsuarioRepository::class,
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class TemaConteudoResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var temaConteudoUseCase: TemaConteudoUseCase

    @MockBean
    private lateinit var temaConteudoGatewayAdapter: TemaConteudoDatabaseGatewayAdapter

    @MockBean
    private lateinit var temaConteudoRepository: TemaConteudoRepository

    @MockBean
    private lateinit var usuarioRepository: UsuarioRepository

    @Test
    fun `Deve retornar ok quando lista tema conteudo com curriculo BNCC`() {

        val resultado: Set<TemaConteudo> = setOf(
                criarTemaConteudoBNCC(),
            )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(Curriculo.BNCC.name)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.BNCC.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Deve retornar ok quando lista tema conteudo com curriculo PCN`() {

        val resultado: Set<TemaConteudo> = setOf(
            criarTemaConteudoPCN(),
        )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(Curriculo.PCN.name)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.PCN.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Deve retornar ok quando lista tema conteudo e retornar nenhum dado com curriculo PCN`() {

        val resultado: Set<TemaConteudo> = setOf(
            criarTemaConteudoPCN(),
        )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(Curriculo.BNCC.name)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .contentType(MediaType.APPLICATION_JSON)
                .param("idCurriculo", "1")
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Não deve retornar uma lista de tema conteúdos`() {

        val resultado: Set<TemaConteudo> = setOf(
        )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(anyString())
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.PCN.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Deve retornar ok quando lista tema conteudo e retornar nenhum dado com curriculo BNCC`() {

        val resultado: Set<TemaConteudo> = setOf(
            criarTemaConteudoBNCC(),
        )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(Curriculo.PCN.name)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.BNCC.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

}
