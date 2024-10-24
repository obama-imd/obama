package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
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
@SpringBootTest(classes = [PlanoAulaGatewayAdapter::class, PlanoAulaRepository::class])
class PlanoAulaGatewayAdapterTest {
    @Autowired
    private lateinit var planoAulaGatewayAdapter: PlanoAulaGatewayAdapter

    @MockBean
    private lateinit var planoAulaRepository: PlanoAulaRepository

    companion object {
        const val pageSize = 10
        const val titulo = "teste"
        const val email = "usuario_ativo123@ufrn.com"
    }

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val pageable: Pageable = Pageable.ofSize(pageSize)
        val autor = criarUsuarioAtivo().toEntity()

        val resultadoVazio: Page<PlanoAulaEntity> = Page.empty()

        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorTitulo(autor, null, pageable)
        ).thenReturn(resultadoVazio)

        var resultadoGateway: Page<PlanoAula>? = null

        assertDoesNotThrow {
            resultadoGateway = planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(autor, null, pageable)
        }

        Assertions.assertTrue(resultadoGateway!!.isEmpty)
        Assertions.assertEquals(resultadoVazio, resultadoGateway)
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val pageable: Pageable = Pageable.ofSize(pageSize)
        val autor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAulaEntity> = PageImpl(
            listOf(
                criarPlanoAula().toEntity(),
                criarPlanoAula().toEntity()
            )
        )

        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorTitulo(autor, PlanoAulaGatewayAdapterTest.titulo, pageable)
        ).thenReturn(resultado)

        var resultadoGateway: Page<PlanoAula>? = null

        assertDoesNotThrow {
            resultadoGateway =
                planoAulaGatewayAdapter.buscarPlanosAulaPorTitulo(autor, PlanoAulaGatewayAdapterTest.titulo, pageable)
        }

        Assertions.assertEquals(resultadoGateway?.isEmpty, false)
    }

    @Test
    fun `Deve fazer busca no repository e achar plano de aula com coautores`() {
        val pageable: Pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAulaEntity> = PageImpl(
            listOf(
                criarPlanoAulaComCoautores().toEntity(),
                criarPlanoAulaComCoautores().toEntity()
            )
        )

        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorCoautor(email, null, pageable)
        ).thenReturn(resultado)

        var resultadoFinal: Page<PlanoAula> = Page.empty()
        assertDoesNotThrow {
            resultadoFinal = planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(coautor, null, pageable)
        }

        Assertions.assertFalse(resultadoFinal.isEmpty)
        Assertions.assertNotNull(resultadoFinal.first()!!.getCoautores())
        Assertions.assertEquals(resultadoFinal.first()!!.getCoautores()!!.first().email, email)
    }

    @Test
    fun `Deve fazer busca no repository e não achar nenhum plano de aula com coautor específico`(){
        val pageable: Pageable = Pageable.ofSize(pageSize)
        val coautor = criarUsuarioAtivo().toEntity()

        val resultado: Page<PlanoAulaEntity> = Page.empty()
        Mockito.`when`(
            planoAulaRepository.buscarPlanosAulaPorCoautor(email, null, pageable)
        ).thenReturn(resultado)

        var resultadoFinal: Page<PlanoAula> = Page.empty()
        assertDoesNotThrow {
            resultadoFinal = planoAulaGatewayAdapter.buscarPlanosAulaPorCoautor(coautor, null, pageable)
        }

        Assertions.assertTrue(resultadoFinal.isEmpty)
        Assertions.assertEquals(resultadoFinal, resultado)
    }

    @Test
    fun `Deve buscar plano de aula por id e encontrar`() {
        val planoAula = criarPlanoAula()
        val planoAulaEntity = planoAula.toEntity()

        Mockito.`when`(planoAulaRepository.buscarPlanoAulaPorId(1)).thenReturn(planoAulaEntity)

        val resultado = planoAulaGatewayAdapter.buscarPlanoAulaPorId(1)

        Assertions.assertEquals(planoAula, resultado)
    }

    @Test
    fun `Não deve encontrar plano de aula por id`() {
        Mockito.`when`(planoAulaRepository.buscarPlanoAulaPorId(1)).thenReturn(null)

        assertThrows<PlanoAulaNaoEncontradoException> {
            val resultado = planoAulaGatewayAdapter.buscarPlanoAulaPorId(1)
        }

    }
}