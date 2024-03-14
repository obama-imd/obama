package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCase
import br.ufrn.imd.obama.oa.infrastructure.adapter.NivelEnsinoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.NivelEnsinoConfig
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import br.ufrn.imd.obama.oa.util.criarNivelEnsino
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
        NivelEnsinoConfig::class,
        NivelEnsinoDatabaseGatewayAdapter::class,
        NivelEnsinoUseCase::class,
        NivelEnsinoRepository::class,
        NivelEnsinoResourceImpl::class,
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class NivelEnsinoResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var nivelEnsinoUseCase: NivelEnsinoUseCase

    @MockBean
    private lateinit var nivelEnsinoGatewayAdapter: NivelEnsinoDatabaseGatewayAdapter

    @MockBean
    private lateinit var nivelEnsinoRepository: NivelEnsinoRepository

    @Test
    fun `Deve retornar ok quando lista nivel ensino`() {

        var resultado: Set<NivelEnsino> = setOf(
                criarNivelEnsino(),
            )

        `when`(
            nivelEnsinoUseCase.listarNiveisEnsino()
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/nivelensino")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

}
