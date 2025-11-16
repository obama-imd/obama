package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import jakarta.persistence.EntityManager
import org.springframework.transaction.annotation.Transactional
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
@Import(value = [TokenConfiguration::class, SecurityConfiguration::class])
@AutoConfigureMockMvc
@ActiveProfiles(profiles = ["test"])
class ObjetoAprendizagemResourceImplTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired private lateinit var objetoAprendizagemUseCase: ObjetoAprendizagemUseCase

    @Autowired private lateinit var entityManager: EntityManager

    @Test
    @Transactional
    fun `Deve retornar OK quando informado id existente`() {
        val resultado = adicionarObjetoAprendizagemExistente()

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/oa/{id}", resultado.id)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    private fun adicionarObjetoAprendizagemExistente(): ObjetoAprendizagemEntity {
        val resultado: ObjetoAprendizagemEntity = criarObjetoAprendizagem().toEntity()

        val sql = """
            INSERT INTO objeto_aprendizagem(
                id,
                ativo,
                data_lancamento,
                descricao,
                nome,
                caminho_thumbnail,
                quantidade_acessos,
                versao
            ) VALUES (
                :id,
                :ativo,
                :dataLancamento,
                :descricao,
                :nome,
                :caminhoThumbnail,
                :quantidadeAcessos,
                :versao
            );
        """.trimIndent()

        entityManager.createNativeQuery(sql)
            .setParameter("id", resultado.id)
            .setParameter("ativo", resultado.ativo)
            .setParameter("dataLancamento", resultado.dataLancamento)
            .setParameter("descricao", resultado.descricao)
            .setParameter("nome", resultado.nome)
            .setParameter("caminhoThumbnail", resultado.thumbnailPath)
            .setParameter("quantidadeAcessos", resultado.quantidadeAcessos)
            .setParameter("versao", resultado.versao)
            .executeUpdate()

        return resultado
    }

    @Test
    fun `Deve retornar NOT FOUND quando informado id inexistente`() {
        val idInexistente = 0L

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/oa/{id}", idInexistente)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `Deve retornar retornar lista de objetos`() {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/oa")
                                .param("page", "0")
                                .param("size", "10")
                                .param("nome", "Math")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }
}
