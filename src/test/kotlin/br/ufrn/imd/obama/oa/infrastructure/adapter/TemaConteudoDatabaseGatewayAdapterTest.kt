package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
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
    fun `Deve fazer busca no repository nenhum dado`() {

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
}
