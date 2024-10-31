package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.oa.domain.exception.AnoEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.NivelEnsinoNaoEncontradoException
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

    @Tes@Operation(summary = "Endpoint para salvar plano de aula")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Plano de aula salvo",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PlanoAulaResponse::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Usuário não autenticado",
        ),
        ApiResponse(
            responseCode = "400",
            description = "Id do nível ensino inválido; A duração em minutos é menor do que 0; Algum dos ids da lista de ids das disciplinas envolvidas é inválido; Id do ano ensino inválido",
        ),
        ApiResponse(
            responseCode = "400",
            description = "Id do ano ensino inválido",
        )
    ])

    fun salvarPlanoAula(
        usuarioDetails: UserDetails,
        planoAulaSalvarRequest: PlanoAulaSalvarRequest
        ): ResponseEntity<PlanoAulaResponse>t
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
        val coautor = criarUsuarioAtivo().toEntity()
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
        Assertions.assertEquals(paginas.first().getCoautores()!!.first().email, coautor.email)
    }

    @Test
    fun `Deve achar planos passando coautor existente e título`() {
        val pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo().toEntity()
        val resultado = PageImpl(
            listOf(criarPlanoAulaComCoautores(), criarPlanoAulaComCoautores())
        )

        Mockito.`when`(
            planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(
                coautor, "teste", pageable
            )
        ).thenReturn(resultado)

        var paginas : Page<PlanoAula> = Page.empty();
        assertDoesNotThrow {
            paginas = planoAulaUseCase.buscarPlanosAulaPorCoautor(
                coautor, "teste", pageable
            )
        }
    }
}
