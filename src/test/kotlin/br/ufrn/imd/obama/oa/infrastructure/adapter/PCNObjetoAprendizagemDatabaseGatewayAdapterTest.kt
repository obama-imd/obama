package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarListaObjetoAprendizagem
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [PCNObjetoAprendizagemDatabaseGatewayAdapter::class, ObjetoAprendizagemRepository::class])
class PCNObjetoAprendizagemDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: PCNObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = Page.empty()

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
                nome,
                null,
                null,
                null,
                null,
                pageable
            )
        ).thenReturn(
            resultado
        )

        var oas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            oas = gatewayAdapter.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
            )
        }
        Assertions.assertEquals(oas?.isEmpty, true)
    }

    @Test
    fun `Deve fazer busca no repository e encontrar algum dado`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = PageImpl(
            listOf(
                criarObjetoAprendizagem().toEntity()
            )
        )

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
                nome,
                null,
                null,
                null,
                null,
                pageable
            )
        ).thenReturn(
            resultado
        )

        var oas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            oas = gatewayAdapter.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
            )
        }

        Assertions.assertEquals(oas?.isEmpty, false)
    }


    @Test
    fun `Deve retornar uma lista ordenada por nome PCN`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = PageImpl(
            criarListaObjetoAprendizagem().map { it.toEntity() }
        )

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
                nome,
                null,
                null,
                null,
                null,
                pageable
            )
        ).thenReturn(
            resultado
        )

        var oas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            oas = gatewayAdapter.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
            )
        }

        Assertions.assertEquals(oas?.isEmpty, false)
        Assertions.assertEquals(oas!!.content.get(0).nome, resultado.content.get(0).nome)
        Assertions.assertEquals(oas!!.content.get(1).nome, resultado.content.get(1).nome)
    }
}
