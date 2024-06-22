package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.infrastructure.adapter.AnoEnsinoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.criarAnoEnsino
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


@SpringBootTest( classes = [
    AnoEnsinoUseCaseImpl::class,
    AnoEnsinoDatabaseGatewayAdapter::class
])
@ActiveProfiles(profiles = ["test"])
class AnoEnsinoUseCaseImplTest {

    @Autowired
    private lateinit var anoEnsinoUseCase: AnoEnsinoUseCaseImpl

    @MockBean
    private lateinit var anoEnsinoDatabaseGateway: AnoEnsinoDatabaseGatewayAdapter

    @Test
    fun `Deve listar e retornar algum dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val result: Page<AnoEnsino> = PageImpl(
            listOf(
                criarAnoEnsino(),
                criarAnoEnsino()
            )
        )

        `when`(
            anoEnsinoDatabaseGateway.listarAnosEnsino(pageable)
        ).thenReturn(
            result
        )

        var anosEnsino: Page<AnoEnsino>? = null

        assertDoesNotThrow {
            anosEnsino = anoEnsinoUseCase.listarAnosEnsino(pageable)
        }

        Assertions.assertEquals(
            anosEnsino?.isEmpty,
            false
        )
    }

    @Test
    fun `Deve listar e retornar nenhum dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val result: Page<AnoEnsino> = PageImpl(
            listOf()
        )

        `when`(
            anoEnsinoDatabaseGateway.listarAnosEnsino(pageable)
        ).thenReturn(
            result
        )

        var anosEnsino: Page<AnoEnsino>? = null

        assertDoesNotThrow {
            anosEnsino = anoEnsinoUseCase.listarAnosEnsino(pageable)
        }

        Assertions.assertEquals(
            anosEnsino?.isEmpty,
            true
        )
    }

}
