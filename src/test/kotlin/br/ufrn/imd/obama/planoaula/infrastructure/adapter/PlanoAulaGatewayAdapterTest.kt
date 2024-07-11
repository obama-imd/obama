package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.util.criarHabilidade
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.planoaula.util.criarPlanoAula
import br.ufrn.imd.obama.planoaula.util.criarPlanoAulaComStatusRemovido
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [PlanoAulaGatewayAdapter::class, PlanoAulaRepository::class])
class PlanoAulaGatewayAdapterTest {
    @Autowired
    private lateinit var planoAulaGatewayAdapter: PlanoAulaGatewayAdapter

    @MockBean
    private lateinit var planoAulaRepository: PlanoAulaRepository

    companion object{
        const val pageSize = 10
        const val titulo = "teste"
    }

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val pageable: Pageable = Pageable.ofSize(PlanoAulaGatewayAdapterTest.pageSize)
        var resultadoVazio: Page<PlanoAulaEntity> = Page.empty()

        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorTitulo(null, pageable)
        ).thenReturn(resultadoVazio)

        var resultadoGateway: Page<PlanoAula>? = null

        assertDoesNotThrow {
            resultadoGateway = planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(null, pageable)
        }

        Assertions.assertTrue(resultadoGateway!!.isEmpty)
        Assertions.assertEquals(resultadoVazio, resultadoGateway)
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val pageable: Pageable = Pageable.ofSize(PlanoAulaGatewayAdapterTest.pageSize)

        val resultado: Page<PlanoAulaEntity> = PageImpl(
            listOf(
                criarPlanoAula().toEntity(),
                criarPlanoAula().toEntity()
            )
        )

        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorTitulo(PlanoAulaGatewayAdapterTest.titulo, pageable)
        ).thenReturn(resultado)

        var resultadoGateway: Page<PlanoAula>? = null

        assertDoesNotThrow {
            resultadoGateway = planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(PlanoAulaGatewayAdapterTest.titulo, pageable)
        }

        Assertions.assertEquals(resultadoGateway?.isEmpty, false)
    }
}
