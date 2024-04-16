package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.infrastructure.adapter.HabilidadeGatewayAdapter
import br.ufrn.imd.obama.oa.util.criarHabilidade
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
@SpringBootTest(classes = [HabilidadeUseCaseImpl:class, HabilidadeGatewayAdapter::class])
class HabilidadeUsecaseImplTest {
    @Autowired
    private lateinit var habilidadeUseCase: HabilidadeUseCaseImpl

    @MockBean
    private lateinit var habilidadeGateway: HabilidadeGatewayAdapter

    @Test
    fun `Deve achar alguma Habilidade`() {

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<Habilidade> = PageImpl(
            listOf(
                criarHabilidade(),
                criarHabilidade()
            ),
        )

        Mockito.`when`(
            habilidadeGateway.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
                1L,
                1L,
                pageable,
            )
        ).thenReturn(resultado);

        var paginas: Page<Habilidade>? = null

        assertDoesNotThrow {
            paginas = habilidadeUseCase.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(1L, 1L, pageable)
        }

        Assertions.assertEquals(paginas?.isEmpty, false)
    }
}