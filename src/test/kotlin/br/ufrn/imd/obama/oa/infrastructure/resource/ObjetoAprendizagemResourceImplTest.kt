package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.BNCCObjetoAprendizagemDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.configuration.OaConfig
import br.ufrn.imd.obama.oa.infrastructure.handler.ObjetoAprendizagemExceptionHandler
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.NOME_BNCC_CURRICULO
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.NoSuchBeanDefinitionException
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
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [ObjetoAprendizagemResourceImpl::class, ObjetoAprendizagemUseCaseImpl::class])
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
@ContextConfiguration(classes = [
    ObjetoAprendizagemExceptionHandler::class,
    ObjetoAprendizagem::class,
    OaConfig::class,
    BNCCObjetoAprendizagemDatabaseGatewayAdapter::class,
    ObjetoAprendizagemRepository::class
])
class ObjetoAprendizagemResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var buscarOa: br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase

    @MockBean
    private lateinit var bnccObjetoAprendizagemDatabaseGatewayAdapter: BNCCObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository


    @Test
    fun `Deve retornar bad request quando informa um curriculo inválido`() {
        var pageable: Pageable = Pageable.ofSize(10)

        `when`(
            buscarOa.buscarPorParametros(
                pageable,
                "Math",
                null,
                null,
                null,
                null,
                null,
                NOME_CURRICULO_INVALIDO
            )
        ).thenThrow(NoSuchBeanDefinitionException::class.java)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
                .param("nome", "Math")
                .param("curriculo", NOME_CURRICULO_INVALIDO)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())

    }

    @Test
    fun `Deve retornar retornar lista de objetos com curriculo válido`() {
        var resultado: Page<br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem> = PageImpl(
            listOf(
                criarObjetoAprendizagem(),
                criarObjetoAprendizagem()
            ),
        )

        var pageable: Pageable = Pageable.ofSize(10)

        `when`(
            buscarOa.buscarPorParametros(
                pageable,
                "Math",
                null,
                null,
                null,
                null,
                null,
                NOME_BNCC_CURRICULO
            )
        ).thenReturn(
            resultado
        )

        mockMvc.perform (
                MockMvcRequestBuilders.get("/v1/oa")
                    .param("page", "0")
                    .param("size", "10")
                    .param("nome", "Math")
                    .param("curriculo", NOME_BNCC_CURRICULO)
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

        verify(buscarOa, times(1)).buscarPorParametros(
            pageable,
            "Math",
            null,
            null,
            null,
            null,
            null,
            NOME_BNCC_CURRICULO
        )
    }
}
