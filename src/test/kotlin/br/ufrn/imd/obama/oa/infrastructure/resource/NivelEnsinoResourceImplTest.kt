package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCase
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class NivelEnsinoResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private  lateinit var nivelEnsinoUseCase: NivelEnsinoUseCase

    @Test
    fun `Deve retornar ok quando lista nivel ensino`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/nivelensino")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
         .andExpect(status().isOk())
    }

}
