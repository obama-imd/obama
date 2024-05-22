package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import br.ufrn.imd.obama.oa.util.criarTemaConteudoBNCC
import br.ufrn.imd.obama.oa.util.criarTemaConteudoPCN
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
            temaConteudoRepository.buscarTodosPeloCurriculo(Curriculo.BNCC)
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.listarTemaConteudo(Curriculo.BNCC)
        }
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val resultado: List<TemaConteudoEntity> = listOf(
            criarTemaConteudoBNCC().toEntity()
        )

        `when`(
            temaConteudoRepository.buscarTodosPeloCurriculo(Curriculo.BNCC)
        ).thenReturn(
            resultado
        )

        var temaConteudos: Set<TemaConteudo> = setOf()

        assertDoesNotThrow {
            temaConteudos = gatewayAdapter.listarTemaConteudo(Curriculo.BNCC)
        }

        Assertions.assertEquals(temaConteudos.isEmpty(), false)
    }

    @Test
    fun `Deve fazer busca no repository e achar nenhum dado com curriculo PCN`() {

        val resultado: List<TemaConteudoEntity> = listOf()

        `when`(
            temaConteudoRepository.buscarTodosPeloCurriculo(Curriculo.PCN)
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.listarTemaConteudo(Curriculo.PCN)
        }
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado com curriculo PCN`() {

        val resultado: List<TemaConteudoEntity> = listOf(
            criarTemaConteudoPCN().toEntity()
        )

        `when`(
            temaConteudoRepository.buscarTodosPeloCurriculo(Curriculo.PCN)
        ).thenReturn(
            resultado
        )

        var temaConteudos: Set<TemaConteudo> = setOf()

        assertDoesNotThrow {
            temaConteudos = gatewayAdapter.listarTemaConteudo(Curriculo.PCN)
        }

        Assertions.assertEquals(temaConteudos.isEmpty(), false)
    }
}
