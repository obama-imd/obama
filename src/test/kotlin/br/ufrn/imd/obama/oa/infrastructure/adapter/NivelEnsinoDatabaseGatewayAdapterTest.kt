package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import br.ufrn.imd.obama.oa.util.criarNivelEnsino
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [NivelEnsinoDatabaseGatewayAdapter::class, NivelEnsinoRepository::class])
class NivelEnsinoDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: NivelEnsinoDatabaseGatewayAdapter

    @MockBean
    private lateinit var nivelEnsinoRepository: NivelEnsinoRepository

    @Test
    fun `Deve fazer busca no repository nenhum dado`() {

        val resultado: List<NivelEnsinoEntity> = listOf()

        `when`(
            nivelEnsinoRepository.findAll()
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.listarNivelEnsino()
        }
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val resultado: List<NivelEnsinoEntity> = listOf(
            criarNivelEnsino().toEntity()
        )

        `when`(
            nivelEnsinoRepository.findAll()
        ).thenReturn(
            resultado
        )

        var niveisEnsino: Set<NivelEnsino> = setOf()

        assertDoesNotThrow {
            niveisEnsino = gatewayAdapter.listarNivelEnsino()
        }

        Assertions.assertEquals(niveisEnsino.isEmpty(), false)
    }
}
