package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.domain.usecase.HabilidadeUseCase
import br.ufrn.imd.obama.oa.infrastructure.adapter.HabilidadeGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.HabilidadeConfig
import br.ufrn.imd.obama.oa.infrastructure.repository.HabilidadeRepository
import br.ufrn.imd.obama.oa.util.criarHabilidade
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityFilter
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenService
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(
    classes = [
        HabilidadeConfig::class,
        HabilidadeGatewayAdapter::class,
        HabilidadeUseCase::class,
        HabilidadeRepository::class,
        HabilidadeResourceImpl::class,
        SecurityConfiguration::class,
        SecurityFilter::class,
        TokenService::class,
        UsuarioDatabaseGatewayAdapter::class,
        UsuarioRepository::class
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class HabilidadeResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var habilidadeUseCase: HabilidadeUseCase

    @MockBean
    private lateinit var habilidadeGatewayAdapter: HabilidadeGatewayAdapter

    @MockBean
    private lateinit var habilidadeRepository: HabilidadeRepository

    @MockBean
    private lateinit var usuarioRepository: UsuarioRepository

    @Test
    fun `Deve retornar ok quando lista habilidades passando os dois parametros corretos`() {
        var pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<Habilidade> = PageImpl(
            listOf(
                criarHabilidade(),
            ),
        )

        Mockito.`when`(
            habilidadeUseCase.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
                1L,
                1L,
                pageable
            )
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/habilidade")
                .contentType(MediaType.APPLICATION_JSON)
                .param("anoEnsinoId", "1")
                .param("temaConteudoId", "1")
                .param("page", "0")
                .param("size", "10")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
