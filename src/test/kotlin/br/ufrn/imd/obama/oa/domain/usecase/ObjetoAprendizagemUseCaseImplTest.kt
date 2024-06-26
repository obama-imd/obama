package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.adapter.ObjetoAprendizagemDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
@SpringBootTest(
    classes = [
        ObjetoAprendizagemUseCaseImpl::class
    ]
)
class ObjetoAprendizagemUseCaseImplTest {

    @Autowired
    private lateinit var objetoAprendizagemUseCase: ObjetoAprendizagemUseCaseImpl

    @MockBean
    private lateinit var objetoAprendizagemDatabaseGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter

    @Test
    fun `Deve achar Objeto de Aprendizagem`() {
        val resultado = criarObjetoAprendizagem()

        `when`(
            objetoAprendizagemDatabaseGatewayAdapter.procurarPorID(resultado.id)
        ).thenReturn(
            resultado
        )

        var oa: ObjetoAprendizagem?= null

        assertDoesNotThrow {
            oa = objetoAprendizagemUseCase.buscarPorId(resultado.id)
        }

        Assertions.assertEquals(resultado, oa)
    }

    @Test
    fun `Deve retornar OANaoEncontradoException`() {
        val idInexistente = 0L

        `when`(
            objetoAprendizagemDatabaseGatewayAdapter.procurarPorID(idInexistente)
        ).thenThrow(OANaoEncontradoException::class.java)

        Assertions.assertThrows(OANaoEncontradoException::class.java) {
            objetoAprendizagemUseCase.buscarPorId(idInexistente)
        }
    }

    @Test
    fun `Deve achar algum objeto de aprendizagem`() {
        val curriculo = Curriculo.BNCC.name
        val nome= "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = PageImpl(
            listOf(
                criarObjetoAprendizagem(),
                criarObjetoAprendizagem()
            ),
        )

        `when`(
            objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        ).thenReturn(resultado);

        var paginas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            paginas = objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum objeto de aprendizagem`() {
        val nome= "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = Page.empty(pageable)

        `when`(
            objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        ).thenReturn(resultado);

        var paginas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            paginas = objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, true)
    }

}
