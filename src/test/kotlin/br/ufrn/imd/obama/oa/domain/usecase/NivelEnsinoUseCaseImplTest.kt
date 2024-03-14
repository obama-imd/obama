package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.adapter.NivelEnsinoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.NOME_BNCC_CURRICULO
import br.ufrn.imd.obama.oa.util.criarNivelEnsino
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [NivelEnsinoUseCaseImpl::class, NivelEnsinoDatabaseGatewayAdapter::class])
class NivelEnsinoUseCaseImplTest {

    @Autowired
    private lateinit var nivelEnsinoUseCase: NivelEnsinoUseCaseImpl

    @MockBean
    private lateinit var databaseGateway: NivelEnsinoDatabaseGatewayAdapter

    @Test
    fun `Deve achar algum nivel de ensino`() {

        val resultado: Set<NivelEnsino> = setOf(
                criarNivelEnsino(),
                criarNivelEnsino()
        )

        `when`(
            databaseGateway.listarNivelEnsino()
        ).thenReturn(resultado)

        var descritor: Set<NivelEnsino>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarNiveisEnsino()
        }

        Assertions.assertEquals(descritor?.isEmpty(), false)
    }

    @Test
    fun `Deve achar nenhum nivel de ensino`() {

        var resultado: Set<NivelEnsino> = setOf()

        `when`(
            databaseGateway.listarNivelEnsino()
        ).thenReturn(resultado)

        var descritor: Set<NivelEnsino>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarNiveisEnsino()
        }

        Assertions.assertEquals(descritor?.isEmpty(), true)
    }
}
