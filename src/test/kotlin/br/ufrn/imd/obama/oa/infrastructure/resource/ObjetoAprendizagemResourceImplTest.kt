package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
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
class ObjetoAprendizagemResourceImplTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private  lateinit var objetoAprendizagemUseCase: ObjetoAprendizagemUseCase

    @Autowired
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve retornar OK quando informado id existente`() {
        val resultado = criarObjetoAprendizagem().toEntity()

        objetoAprendizagemRepository.save(resultado)

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

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa/{id}", idInexistente)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `Deve retornar retornar lista de objetos`() {

        mockMvc.perform (
            MockMvcRequestBuilders.get("/v1/oa")
                .param("page", "0")
                .param("size", "10")
                .param("nome", "Math")
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

    }
}
