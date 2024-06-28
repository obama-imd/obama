package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
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


@SpringBootTest(classes = [
    AnoEnsinoDatabaseGatewayAdapter::class,
    AnoEnsinoRepository::class
])
@ActiveProfiles(profiles = ["test"])
class AnoEnsinoDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var anoEnsinoDatabaseGatewayAdapter: AnoEnsinoDatabaseGatewayAdapter

    @MockBean
    private lateinit var anoEnsinoRepository: AnoEnsinoRepository

    @Test
    fun `Deve buscar e retornar algum dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val result: Page<AnoEnsinoEntity> = PageImpl(
            listOf(
                criarAnoEnsino().toEntity(),
                criarAnoEnsino().toEntity()
            )
        )

        `when`(
            anoEnsinoRepository.findAll(pageable)
        ).thenReturn(
            result
        )

        var anosEnsino: Page<AnoEnsino>? = null

        assertDoesNotThrow {
            anosEnsino = anoEnsinoDatabaseGatewayAdapter.listarAnosEnsino(pageable)
        }

        Assertions.assertEquals(
            anosEnsino?.isEmpty,
            false
        )
    }

    @Test
    fun `Deve buscar e retornar nenhum dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val result: Page<AnoEnsinoEntity> = PageImpl(
            listOf()
        )

        `when`(
            anoEnsinoRepository.findAll(pageable)
        ).thenReturn(
            result
        )

        var anosEnsino: Page<AnoEnsino>? = null

        assertDoesNotThrow {
            anosEnsino = anoEnsinoDatabaseGatewayAdapter.listarAnosEnsino(pageable)
        }

        Assertions.assertEquals(
            anosEnsino?.isEmpty,
            true
        )
    }

}
