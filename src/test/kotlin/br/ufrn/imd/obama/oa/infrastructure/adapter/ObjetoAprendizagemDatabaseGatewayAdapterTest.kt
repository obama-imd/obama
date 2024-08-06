package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.domain.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
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
@SpringBootTest(classes = [
    ObjetoAprendizagemDatabaseGatewayAdapter::class,
    ObjetoAprendizagemRepository::class
])
class ObjetoAprendizagemDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository e encontrar uma OA`() {
        val resultado = criarObjetoAprendizagem()

        `when`(
            objetoAprendizagemRepository.buscarPorId(resultado.id)
        ).thenReturn(
            resultado.toEntity()
        )

        var oa: ObjetoAprendizagem? = null

        assertDoesNotThrow {
            oa = gatewayAdapter.procurarPorID(resultado.id)
        }

        Assertions.assertEquals(resultado, oa)
    }

    @Test
    fun `Deve fazer busca no repository e retornar OANaoEncontradoException`() {
        val idInexistente = 0L
        val resultadoNulo = null

        `when`(
            objetoAprendizagemRepository.buscarPorId(idInexistente)
        ).thenReturn(
            resultadoNulo
        )

        Assertions.assertThrows(OANaoEncontradoException::class.java) {
            gatewayAdapter.procurarPorID(idInexistente)
        }
    }

    @Test
    fun `Deve buscar lista de oas`() {
        val nome = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = Page.empty()

        `when`(
            objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoETemaConteudoEDescritorEHabilidade(
                nome.uppercase(),
                null,
                null,
                null,
                null,
                pageable,
                null
            )
        ).thenReturn(
            resultado
        )

        var oas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            oas = gatewayAdapter.procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdAndHabilidadeId(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
            )
        }
        Assertions.assertEquals(oas?.isEmpty, true)
    }
}
