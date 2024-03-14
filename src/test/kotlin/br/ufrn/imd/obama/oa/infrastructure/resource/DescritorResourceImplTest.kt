package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCase
import br.ufrn.imd.obama.oa.infrastructure.adapter.DescritorDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.DescritorConfig
import br.ufrn.imd.obama.oa.infrastructure.repository.DescritorRepository
import br.ufrn.imd.obama.oa.util.criarDescritor
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
        DescritorConfig::class,
        DescritorDatabaseGatewayAdapter::class,
        DescritorUseCase::class,
        DescritorRepository::class,
        DescritorResourceImpl::class,
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class DescritorResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var descritorUseCase: DescritorUseCase

    @MockBean
    private lateinit var descritorDatabaseGatewayAdapter: DescritorDatabaseGatewayAdapter

    @MockBean
    private lateinit var descritorRepository: DescritorRepository

    @Test
    fun `Deve retornar ok quando lista descritores`() {
        var pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<Descritor> = PageImpl(
            listOf(
                criarDescritor(),
            ),
        )

        `when`(
            descritorUseCase.listarDescritor(
                pageable
            )
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/descritor")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

}
