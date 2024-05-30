package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCase
import br.ufrn.imd.obama.oa.util.criarDescritor
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
class DescritorResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private  lateinit var descritorUseCase: DescritorUseCase

    @Test
    fun `Deve retornar ok quando lista descritores`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/descritor")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

}
