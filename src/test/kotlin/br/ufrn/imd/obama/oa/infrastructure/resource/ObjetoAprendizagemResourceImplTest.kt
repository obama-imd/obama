package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.handler.ObjetoAprendizagemExceptionHandler
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
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
        ObjetoAprendizagemExceptionHandler::class,
        ObjetoAprendizagemUseCase::class,
        ObjetoAprendizagemRepository::class,
        ObjetoAprendizagemResourceImpl::class,
    ]
)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = [
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    DataSourceTransactionManagerAutoConfiguration::class
])
class ObjetoAprendizagemResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private  lateinit var objetoAprendizagemUseCase: ObjetoAprendizagemUseCaseImpl

    @Test
    fun `Deve retornar OK quando informado id existente`() {
        val resultado = criarObjetoAprendizagem()

        `when`(
            objetoAprendizagemUseCase.buscarPorId(resultado.id)
        ).thenReturn(
            resultado
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa/{id}", resultado.id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

    }

    @Test
    fun `Deve retornar NOT FOUND quando informado id inexistente`() {
        val idInexistente = 0L

        `when`(
            objetoAprendizagemUseCase.buscarPorId(idInexistente)
        ).thenThrow(NoSuchElementException::class.java)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa/{id}", idInexistente)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

}
