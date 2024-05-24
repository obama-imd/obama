package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.HabilidadeRepository
import br.ufrn.imd.obama.oa.util.criarHabilidade
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import org.mockito.Mockito.`when`
import org.springframework.data.domain.PageImpl


@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [HabilidadeGatewayAdapter::class, HabilidadeRepository::class])
class HabilidadeGatewayAdapterTest {

    @Autowired
    private lateinit var habilidadeGatewayAdapter: HabilidadeGatewayAdapter

    @MockBean
    private lateinit  var habilidadeRepository: HabilidadeRepository

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val pageable: Pageable = Pageable.ofSize(10)
        val resultadoVazio: Page<HabilidadeEntity> = Page.empty()

        `when`(
            habilidadeRepository.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(null, null, pageable)
        ).thenReturn(resultadoVazio)

        var resultadoGateway: Page<Habilidade>? = null
        assertDoesNotThrow {
            resultadoGateway = habilidadeGatewayAdapter.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(null, null, pageable)
        }
        assertTrue(resultadoGateway!!.isEmpty)
        assertEquals(resultadoVazio, resultadoGateway)
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val resultado: Page<HabilidadeEntity> = PageImpl(
            listOf(
                criarHabilidade().toEntity(),
                criarHabilidade().toEntity()
            ),
        )

        val pageable: Pageable = Pageable.ofSize(10)

        `when`(
            habilidadeRepository.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(1L, 1L, pageable)
        ).thenReturn(
            resultado
        )

        var habilidade: Page<Habilidade>? = null

        assertDoesNotThrow {
            habilidade = habilidadeGatewayAdapter.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(1L, 1L, pageable)
        }

        assertEquals(habilidade?.isEmpty, false)
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado passando um dos parametros como nulo`() {

        val resultado: Page<HabilidadeEntity> = PageImpl(
            listOf(
                criarHabilidade().toEntity(),
                criarHabilidade().toEntity()
            ),
        )

        val pageable: Pageable = Pageable.ofSize(10)

        `when`(
            habilidadeRepository.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(null, 1L, pageable)
        ).thenReturn(
            resultado
        )

        var habilidade: Page<Habilidade>? = null

        assertDoesNotThrow {
            habilidade = habilidadeGatewayAdapter.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(null, 1L, pageable)
        }

        assertEquals(habilidade?.isEmpty, false)
    }

}
