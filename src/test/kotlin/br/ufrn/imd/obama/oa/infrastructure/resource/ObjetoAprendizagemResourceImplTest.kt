package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import jakarta.transaction.Transactional
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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
@Transactional
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
    fun `Deve retornar bad request quando informa um curriculo inválido`() {

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/oa")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
                .param("nome", "Math")
                .param("curriculo", NOME_CURRICULO_INVALIDO)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest())

    }

    @Test
    fun `Deve retornar retornar lista de objetos quando o é curriculo BNCC`() {

        mockMvc.perform (
            MockMvcRequestBuilders.get("/v1/oa")
                .param("page", "0")
                .param("size", "10")
                .param("nome", "Math")
                .param("curriculo", Curriculo.BNCC.name)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

    }

    @Test
    fun `Deve retornar retornar lista de objetos quando o é curriculo PCN`() {

        mockMvc.perform (
            MockMvcRequestBuilders.get("/v1/oa")
                .param("page", "0")
                .param("size", "10")
                .param("nome", "Math")
                .param("curriculo", Curriculo.PCN.name)
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }
}
