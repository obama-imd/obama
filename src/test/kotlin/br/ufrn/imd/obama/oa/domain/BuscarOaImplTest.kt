package br.ufrn.imd.obama.oa.domain

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.usecase.BuscarOaImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.BNCCObjetoAprendizagemDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.NOME_BNCC_CURRICULO
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [BuscarOaImpl::class, BNCCObjetoAprendizagemDatabaseGatewayAdapter::class])
class BuscarOaImplTest {

    @Autowired
    private lateinit var buscarOaImpl: BuscarOaImpl;

    @MockBean
    private lateinit var databaseGateway: BNCCObjetoAprendizagemDatabaseGatewayAdapter;

    @Test
    fun `Deve achar algum objeto de aprendizagem quando o curriculo é BNCC`() {
        val curriculo: String = NOME_BNCC_CURRICULO
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = PageImpl(
            listOf(
                criarObjetoAprendizagem(),
                criarObjetoAprendizagem()
            ),
        )

        `when`(
            databaseGateway.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        ).thenReturn(resultado);

        val paginas = buscarOaImpl.buscarPorParametros(
            pageable,
            nome,
            null,
            null,
            null,
            null,
            null,
            curriculo
        )

        Assertions.assertEquals(paginas.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum objeto de aprendizagem quando o curriculo é BNCC`() {
        val curriculo: String = NOME_BNCC_CURRICULO
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = Page.empty(pageable)

        `when`(
            databaseGateway.procurarPorCurriculo(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        ).thenReturn(resultado);

        val paginas = buscarOaImpl.buscarPorParametros(
            pageable,
            nome,
            null,
            null,
            null,
            null,
            null,
            curriculo
        )

        Assertions.assertEquals(paginas.isEmpty, true)
    }

    @Test
    fun `Deve lançar uma exceção quando o nome do curriculo é inválido`() {
        val curriculo: String = NOME_CURRICULO_INVALIDO
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        Assertions.assertThrows(
            NoSuchBeanDefinitionException::class.java
        ) {
            buscarOaImpl.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
                curriculo
            )
        }
    }
}
