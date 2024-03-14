package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
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
@SpringBootTest(classes = [BNCCObjetoAprendizagemDatabaseGatewayAdapter::class, ObjetoAprendizagemRepository::class])
class BNCCObjetoAprendizagemDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: BNCCObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository nenhum dado`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = Page.empty()

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
                nome,
                null,
                null,
                null,
                null,
                pageable
            )
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
            )
        }
    }
}
