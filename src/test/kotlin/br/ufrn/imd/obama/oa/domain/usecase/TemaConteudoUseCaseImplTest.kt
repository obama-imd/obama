package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.domain.exception.CurriculoNaoEncontradoException
import br.ufrn.imd.obama.oa.util.NOME_CURRICULO_INVALIDO
import br.ufrn.imd.obama.oa.util.criarTemaConteudoBNCC
import br.ufrn.imd.obama.oa.util.criarTemaConteudoPCN
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [TemaConteudoUseCaseImpl::class, TemaConteudoDatabaseGatewayAdapter::class])
class TemaConteudoUseCaseImplTest {

    @Autowired
    private lateinit var nivelEnsinoUseCase: TemaConteudoUseCaseImpl

    @MockBean
    private lateinit var databaseGateway: TemaConteudoDatabaseGatewayAdapter

    @Test
    fun `Deve achar algum tema conteúdo para o curriculo BNCC`() {

        val resultado: Set<TemaConteudo> = setOf(
                criarTemaConteudoBNCC(),
                criarTemaConteudoBNCC()
        )

        `when`(
            databaseGateway.listarTemaConteudo(Curriculo.BNCC)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(Curriculo.BNCC.name)
        }

        Assertions.assertEquals(descritor?.isEmpty(), false)
    }

    @Test
    fun `Deve achar nenhum tema conteudo para o curriculo BNCC`() {

        var resultado: Set<TemaConteudo> = setOf()

        `when`(
            databaseGateway.listarTemaConteudo(Curriculo.BNCC)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(Curriculo.BNCC.name)
        }

        Assertions.assertEquals(descritor?.isEmpty(), true)
    }

    @Test
    fun `Deve achar algum tema conteúdo com curriculo PCN`() {

        val resultado: Set<TemaConteudo> = setOf(
            criarTemaConteudoPCN(),
            criarTemaConteudoPCN()
        )

        `when`(
            databaseGateway.listarTemaConteudo(Curriculo.PCN)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(Curriculo.PCN.name)
        }

        Assertions.assertEquals(descritor?.isEmpty(), false)
    }

    @Test
    fun `Deve achar nenhum tema conteudo com curriculo PCN`() {

        var resultado: Set<TemaConteudo> = setOf()

        `when`(
            databaseGateway.listarTemaConteudo(Curriculo.PCN)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(Curriculo.PCN.name)
        }

        Assertions.assertEquals(descritor?.isEmpty(), true)
    }

    @Test
    fun `Deve lançar exceção com curriculo inválido`() {

        assertThrows<CurriculoNaoEncontradoException> {
            nivelEnsinoUseCase.listarTemaConteudos(NOME_CURRICULO_INVALIDO)
        }

    }
}
