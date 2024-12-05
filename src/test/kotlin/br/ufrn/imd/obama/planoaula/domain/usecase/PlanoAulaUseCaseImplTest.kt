package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.oa.domain.exception.AnoEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.NivelEnsinoNaoEncontradoException
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.adapter.PlanoAulaGatewayAdapter
import br.ufrn.imd.obama.planoaula.util.criarPlanoAula
import br.ufrn.imd.obama.planoaula.util.criarPlanoAulaComCoautores
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [PlanoAulaUseCaseImpl::class, PlanoAulaGatewayAdapter::class])
class PlanoAulaUseCaseImplTest {
    @Autowired
    private lateinit var planoAulaUseCase: PlanoAulaUseCaseImpl

    @MockBean
    private lateinit var planoAulaGatewayAdapter: PlanoAulaGatewayAdapter

    companion object {
        const val pageSize = 10
    }

    @Test
    fun `Deve achar plano de aula`() {

        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)
        val autor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAula> = PageImpl(
            listOf(
                criarPlanoAula(),
                criarPlanoAula()
            )
        )

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                autor,
                "teste",
                pageable,
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo(autor, "teste", pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum plano de aula`() {

        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)
        val autor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAula> = Page.empty(pageable)

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                autor,
                "teste",
                pageable,
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo(autor, "teste", pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, true)
    }

    @Test
    fun `Deve achar nenhum plano de aula passando titulo como nulo`() {

        val pageable: Pageable = Pageable.ofSize(PlanoAulaUseCaseImplTest.pageSize)
        val autor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAula> = Page.empty(pageable)

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(
                autor,
                null,
                pageable
            )
        ).thenReturn(resultado);

        var paginas: Page<PlanoAula> = Page.empty(pageable)

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanoAulaPorTitulo(autor, null, pageable)
        }

        Assertions.assertEquals(paginas.isEmpty, true)
    }

    @Test
    fun `Deve achar plano de aula passando id`() {
        Mockito.`when`(planoAulaGatewayAdapter.buscarPlanoAulaPorId(1)).thenReturn(criarPlanoAula())

        val planoAula = planoAulaUseCase.buscarPlanoAulaPorId(1)

        Assertions.assertNotNull(planoAula)
        Assertions.assertEquals(1, planoAula.id)
    }

    @Test
    fun `Deve achar nenhum plano de aula passando id inexistente`() {
        Mockito.`when`(planoAulaGatewayAdapter.buscarPlanoAulaPorId(1))
            .thenThrow(PlanoAulaNaoEncontradoException("Plano de aula não encontrado por ID: 1"))

        assertThrows<PlanoAulaNaoEncontradoException> {
            val planoAula = planoAulaUseCase.buscarPlanoAulaPorId(1)
        }
    }

    @Test
    fun `Deve achar nenhum plano passando usuário que não é coautor de nada`() {
        val pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo().toEntity()
        val resultado: Page<PlanoAula> = Page.empty(pageable)

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(
                coautor,
                null,
                pageable
            )
        ).thenReturn(resultado)

        var paginas: Page<PlanoAula> = Page.empty()
        assertDoesNotThrow {
            paginas = planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(
                coautor, null, pageable
            )
        }

        Assertions.assertEquals(paginas.isEmpty, true)

    }

    @Test
    fun `Deve achar planos passando coautor existente`() {
        val pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo(2L).toEntity()
        val resultado: Page<PlanoAula> = PageImpl(
            listOf(criarPlanoAulaComCoautores())
        )

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(
                coautor, null, pageable
            )
        ).thenReturn(resultado)

        var paginas: Page<PlanoAula> = Page.empty(pageable);

        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanosAulaPorCoautor(
                coautor, null, pageable
            )
        }
        Assertions.assertFalse(paginas::isEmpty)
        Assertions.assertNotNull(paginas.first().getCoautores())
        Assertions.assertEquals(paginas.first().getCoautores()!!.first().getId(), coautor.id)
    }

    @Test
    fun `Deve achar planos passando coautor existente e título`() {
        val pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo(2L).toEntity()
        val resultado = PageImpl(
            listOf(criarPlanoAulaComCoautores(), criarPlanoAulaComCoautores())
        )

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(
                coautor, "teste", pageable
            )
        ).thenReturn(resultado)

        var paginas: Page<PlanoAula> = Page.empty();
        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanosAulaPorCoautor(
                coautor, "teste", pageable
            )
        }
        Assertions.assertFalse(paginas.isEmpty)
    }

    @Test
    fun `Deve salvar plano de aula com sucesso`() {
        val usuario = criarUsuarioAtivo()
        val titulo = "teste"
        val planoAula = criarPlanoAula()

        Mockito.`when`(
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        ).thenReturn(planoAula)

        val planoAulaSalvo = assertDoesNotThrow {
            planoAulaUseCase.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        }

        Assertions.assertNotNull(planoAulaSalvo)
        Assertions.assertEquals(planoAula.getTitulo(), planoAulaSalvo.getTitulo())
    }

    @Test
    fun `Deve lançar exceção ao passar um nivel de ensino inexistente`() {
        val usuario = criarUsuarioAtivo()
        val titulo = "teste"

        Mockito.`when`(
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                null,
                1,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        ).thenThrow(NivelEnsinoNaoEncontradoException("Nível de ensino não encontrado por ID: 1"))

        assertThrows<NivelEnsinoNaoEncontradoException> {
            planoAulaUseCase.salvarPlanoAula(
                usuario,
                null,
                1,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        }
    }

    @Test
    fun `Deve lançar exceção ao passar um ano de ensino inexistente`() {
        val usuario = criarUsuarioAtivo()
        val titulo = "teste"

        Mockito.`when`(
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        ).thenThrow(AnoEnsinoNaoEncontradoException("Ano de ensino não encontrado por ID: 1"))

        assertThrows<AnoEnsinoNaoEncontradoException> {
            planoAulaUseCase.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        }
    }

    @Test
    fun `Deve lançar exceção ao passar um disciplina inexistente`() {
        val usuario = criarUsuarioAtivo()
        val titulo = "teste"

        Mockito.`when`(
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        ).thenThrow(DisciplinaNaoEncontradoException("Disciplina não encontrada por ID: 1"))

        assertThrows<DisciplinaNaoEncontradoException> {
            planoAulaUseCase.salvarPlanoAula(
                usuario,
                null,
                null,
                null,
                1,
                null,
                titulo,
                null,
                null,
                null,
                null,
                null
            )
        }
    }
}
