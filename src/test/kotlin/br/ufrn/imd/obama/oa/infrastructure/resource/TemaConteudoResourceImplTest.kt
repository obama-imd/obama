package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.TemaConteudoConfig
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import br.ufrn.imd.obama.oa.util.criarTemaConteudo
import org.junit.jupiter.api.Test
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
        TemaConteudoDatabaseGatewayAdapter::class,
        TemaConteudoUseCase::class,
        TemaConteudoRepository::class,
        TemaConteudoResourceImpl::class,
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

    @Test
    fun `Deve retornar ok quando lista tema conteudo`() {

        var resultado: Set<TemaConteudo> = setOf(
                criarTemaConteudo(),
            )

        `when`(
            temaConteudoUseCase.listarTemaConteudos(1L)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("idCurriculo", "1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

}
