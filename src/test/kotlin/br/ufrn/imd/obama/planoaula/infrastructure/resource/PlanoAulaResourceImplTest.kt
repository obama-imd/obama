package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class PlanoAulaResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var planoAulaUseCase: PlanoAulaUseCase

    @Autowired
    private lateinit var planoAulaRepository: PlanoAulaRepository

    private val objectMapper = ObjectMapper()

    companion object{
        const val pageSize = 10
        const val titulo = "teste"
    }

    @Test
    @WithMockUser(username = "user", roles = ["PADRAO"])
    fun `Deve retornar 200 ao passar parametros corretos para listar planos de aula`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/planoaula")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", titulo)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isOk())
    }


    @Test
    @WithMockUser(username = "user", roles = ["PADRAO"])
    fun `Deve retornar 200 ao passar titulo como nulo`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/planoaula")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", null)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isOk())
    }

    @Test
    fun `Deve retornar 403 quando o usuario nao estiver autorizado`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/planoaula")
                .contentType(MediaType.APPLICATION_JSON)
                .param("titulo", titulo)
                .param("page", "0")
                .param("size", "10")
        )
            .andDo(print())
            .andExpect(status().isForbidden)
    }
}
