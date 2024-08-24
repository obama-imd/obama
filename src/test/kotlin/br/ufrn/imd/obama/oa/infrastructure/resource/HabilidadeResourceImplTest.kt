package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.HabilidadeUseCase
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
class HabilidadeResourceImplTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private  lateinit var habilidadeUseCase: HabilidadeUseCase

    @Test
    fun `Deve retornar ok quando lista habilidades passando os dois parametros corretos`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/habilidade")
                .contentType(MediaType.APPLICATION_JSON)
                .param("anoEnsinoId", "1")
                .param("temaConteudoId", "1")
                .param("page", "0")
                .param("size", "10")
        ).andDo(print())
        .andExpect(status().isOk())
    }
}
