package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.BuscarOaImpl
import br.ufrn.imd.obama.oa.infrastructure.configuration.BuscarOaConfig
import br.ufrn.imd.obama.oa.infrastructure.handler.ObjetoAprendizagemExceptionHandler
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import java.math.BigDecimal
import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [ObjetoAprendizagemResourceImpl::class, BuscarOaImpl::class])
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
@ContextConfiguration(classes = [
    ObjetoAprendizagemExceptionHandler::class,
    BuscarOaImpl::class,
    BuscarOaConfig::class,
    ObjetoAprendizagemDatabaseGateway::class,
    AbstractBeanFactory::class
])
class ObjetoAprendizagemResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var buscarOaImpl: BuscarOaImpl

    @MockBean
    private lateinit var beanFactory: AbstractBeanFactory

//    @Throws(Exception::class)
//    fun shouldCreatePurchaseSuccess() {
//        val savedPurchaseModel: Unit = createSavedPurchaseModel()
//        Mockito.`when`(createPurchaseUseCase.createPurchase(ArgumentMatchers.any())).thenReturn(savedPurchaseModel)
//        mockMvc.perform(
//            MockMvcRequestBuilders.post("/v1/purchase")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(getCreatePurchaseModelString("Description", BigDecimal("10.33")))
//        ).andDo(MockMvcResultHandlers.print())
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A beautiful description"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(10.33))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value(LocalDate.now().toString()))
//    }

    @Test
    fun `Deve retorn bad request quando informa um curriculo inválido`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa")
                .contentType(MediaType.APPLICATION_JSON)
                .param("nome", "Math")
                .param("curriculo", NOME_CURRICULO_INVALIDO)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())

    }
}
