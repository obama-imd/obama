package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.AnoEnsinoUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class AnoEnsinoResourceImplTest {

        @Autowired
        private lateinit var mockMvc: MockMvc

        @Autowired
        private lateinit var anoEnsinoUseCase: AnoEnsinoUseCase

        @Test
        fun `Deve retornar OK quando lista anos de ensino`() {

            mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/anoEnsino")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("page", "0")
                    .param("size", "20")
            ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
        }

}
