package br.ufrn.imd.obama.oa.domain

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.usecase.BuscarOaImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.BNCCObjetoAprendizagemDatabaseGatewayAdapter
import org.junit.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [BuscarOaImpl::class, BNCCObjetoAprendizagemDatabaseGatewayAdapter::class])
class BuscarOaImplTest(

) {

    @Autowired
    private lateinit var buscarOaImpl: BuscarOaImpl

    @MockBean
    private lateinit var databaseGateway: BNCCObjetoAprendizagemDatabaseGatewayAdapter

    @Test
    fun `Deve achar nenhum objeto de aprendizagem quando o curriculo é BNCC`() {
        val curriculo: String = "BNCC"
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
