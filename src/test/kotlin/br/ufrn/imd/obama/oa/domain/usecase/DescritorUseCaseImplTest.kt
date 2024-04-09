package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.adapter.DescritorDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.NOME_BNCC_CURRICULO
import br.ufrn.imd.obama.oa.util.criarDescritor
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [DescritorUseCaseImpl::class, DescritorDatabaseGatewayAdapter::class])
class DescritorUseCaseImplTest {

    @Autowired
    private lateinit var descritorUseCase: DescritorUseCaseImpl

    @MockBean
    private lateinit var databaseGateway: DescritorDatabaseGatewayAdapter

    @Test
    fun `Deve achar algum objeto de aprendizagem quando o curriculo é BNCC`() {
        val curriculo: String = NOME_BNCC_CURRICULO
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        val resultado: Page<Descritor> = PageImpl(
            listOf(
                criarDescritor(),
                criarDescritor()
            ),
        )

        `when`(
            databaseGateway.listarDescritores(
                pageable
            )
        ).thenReturn(resultado)

        var paginas: Page<Descritor>? = null

        assertDoesNotThrow {
            paginas = descritorUseCase.listarDescritor(
                pageable
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum objeto de aprendizagem quando o curriculo é BNCC`() {
        val curriculo: String = NOME_BNCC_CURRICULO
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<Descritor> = Page.empty(pageable)

        `when`(
            databaseGateway.listarDescritores(
                pageable
            )
        ).thenReturn(resultado);

        var paginas: Page<Descritor>? = null

        assertDoesNotThrow {
            paginas = descritorUseCase.listarDescritor(
                pageable
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, true)
    }
}
