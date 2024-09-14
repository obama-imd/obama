package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@Import(value=
    [
        TokenConfiguration::class,
        SecurityConfiguration::class
    ]
)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
class TemaConteudoResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private  lateinit var temaConteudoUseCase: TemaConteudoUseCase

    @Test
    fun `Deve retornar ok quando lista tema conteudo com curriculo BNCC`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.BNCC.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
         .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `Deve retornar ok quando lista tema conteudo com curriculo PCN`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/temaconteudo")
                .param("curriculo", Curriculo.PCN.name)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
