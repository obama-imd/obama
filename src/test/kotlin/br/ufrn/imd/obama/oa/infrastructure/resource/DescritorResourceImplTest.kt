package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCase
import br.ufrn.imd.obama.usuario.infrastructure.configuration.OldCustomEncoder
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@Import(value=
    [
        TokenConfiguration::class,
        SecurityConfiguration::class,
        BCryptPasswordEncoder::class,
    ]
)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
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
