package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import br.ufrn.imd.obama.oa.util.criarTemaConteudo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [TemaConteudoDatabaseGatewayAdapter::class, TemaConteudoRepository::class])
class TemaConteudoDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: TemaConteudoDatabaseGatewayAdapter

    @MockBean
    private lateinit var temaConteudoRepository: TemaConteudoRepository

    @Test
    fun `Deve fazer busca no repository e achar nenhum dado`() {

        val resultado: List<TemaConteudoEntity> = listOf()

        `when`(
            temaConteudoRepository.findAll()
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.listarTemaConteudo(1L)
        }
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val resultado: List<TemaConteudoEntity> = listOf(
            criarTemaConteudo().toEntity()
        )

        `when`(
            temaConteudoRepository.buscarTodosPeloCurriculo(1L)
        ).thenReturn(
            resultado
        )

        var temaConteudos: Set<TemaConteudo> = setOf()

        assertDoesNotThrow {
            temaConteudos = gatewayAdapter.listarTemaConteudo(1L)
        }

        Assertions.assertEquals(temaConteudos.isEmpty(), false)
    }
}
