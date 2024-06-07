package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions.assertEquals
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
@SpringBootTest(classes = [
    PCNOADatabaseGatewayAdapter::class,
    ObjetoAprendizagemRepository::class
])
class PCNOADatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: PCNOADatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = Page.empty()

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoETemaConteudoEDescritorEHabilidade(
                nome,
                null,
                null,
                null,
                null,
                pageable,
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
        assertEquals(oas?.isEmpty, true)
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
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoETemaConteudoEDescritorEHabilidade(
                nome,
                null,
                null,
                null,
                null,
                pageable,
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

        assertEquals(oas?.isEmpty, false)
    }
}
