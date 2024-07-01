package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.adapter.PlanoAulaGatewayAdapter
import br.ufrn.imd.obama.planoaula.util.criarPlanoAula
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
@SpringBootTest(classes = [PlanoAulaUseCaseImpl::class, PlanoAulaGatewayAdapter::class])
class PlanoAulaUseCaseImplTest {
    @Autowired
    private lateinit var planoAulaUseCase: PlanoAulaUseCaseImpl

    @MockBean
    private lateinit var planoAulaGatewayAdapter: PlanoAulaGatewayAdapter

   companion object{ const val pageSize = 10 }

    @Test
    fun `Deve achar plano de aula`() {

        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)

        var resultado: Page<PlanoAula> = PageImpl(
            listOf(
                criarPlanoAula(),
                criarPlanoAula()
            )
        )

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                "teste",
                pageable,
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo("teste", pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum plano de aula`() {
        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)

        var resultado: Page<PlanoAula> = Page.empty(pageable)

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                "teste",
                pageable,
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo("teste", pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, true)
    }

    @Test
    fun `Deve achar nenhum plano de aula passando titulo como nulo`() {
        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)

        var resultado: Page<PlanoAula> = Page.empty(pageable)

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                null,
                pageable
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo(null, pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, true)
    }


}
