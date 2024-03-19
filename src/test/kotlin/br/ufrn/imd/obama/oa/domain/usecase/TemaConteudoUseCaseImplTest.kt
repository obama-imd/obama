package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.criarTemaConteudo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
    fun `Deve achar algum tema conteúdo`() {

        val resultado: Set<TemaConteudo> = setOf(
                criarTemaConteudo(),
                criarTemaConteudo()
        )

        `when`(
            databaseGateway.listarTemaConteudo(1L)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(1L)
        }

        Assertions.assertEquals(descritor?.isEmpty(), false)
    }

    @Test
    fun `Deve achar nenhum tema conteudo`() {

        var resultado: Set<TemaConteudo> = setOf()

        `when`(
            databaseGateway.listarTemaConteudo(1L)
        ).thenReturn(resultado)

        var descritor: Set<TemaConteudo>? = null

        assertDoesNotThrow {
            descritor = nivelEnsinoUseCase.listarTemaConteudos(1L)
        }

        Assertions.assertEquals(descritor?.isEmpty(), true)
    }
}
