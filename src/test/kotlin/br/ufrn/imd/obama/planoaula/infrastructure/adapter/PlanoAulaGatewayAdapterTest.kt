package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.exception.AnoEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.NivelEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaDuracaoNegativaException
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.planoaula.util.criarPlanoAula
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
import java.util.Optional
import java.time.LocalDateTime


@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [PlanoAulaGatewayAdapter::class, PlanoAulaRepository::class])
class PlanoAulaGatewayAdapterTest {
    @Autowired
    private lateinit var planoAulaGatewayAdapter: PlanoAulaGatewayAdapter

    @MockBean
    private lateinit var planoAulaRepository: PlanoAulaRepository

    @MockBean
    private lateinit var nivelEnsinoRepository: NivelEnsinoRepository

    @MockBean
    private lateinit var anoEnsinoRepository: AnoEnsinoRepository

    @MockBean
    private lateinit var disciplinaRepository: DisciplinaRepository

    companion object {
        const val pageSize = 10
        const val titulo = "teste"
    }

    @Test
    fun `Deve fazer busca no repository e encontrar nenhum dado`() {
        val pageable: Pageable = Pageable.ofSize(PlanoAulaGatewayAdapterTest.pageSize)
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

        val pageable: Pageable = Pageable.ofSize(PlanoAulaGatewayAdapterTest.pageSize)
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
            planoAulaGatewayAdapter.buscarPlanoAulaPorId(1)
        }

    }


    @Test
    fun `Deve salvar plano de aula com sucesso`() {
        // Setup
        val usuario = criarUsuarioAtivo()
        val nivelEnsino = NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF")
        val anoEnsino = AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino)
        val disciplina = DisciplinaEntity(id = 1L, nome = "Matemática")

        // Mocking
        Mockito.`when`(nivelEnsinoRepository.findById(1L)).thenReturn(Optional.of(nivelEnsino))
        Mockito.`when`(anoEnsinoRepository.findById(1L)).thenReturn(Optional.of(anoEnsino))
        Mockito.`when`(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina))

        val planoAulaEntity = PlanoAulaEntity(
            id = 0,
            dataCadastro = LocalDateTime.now(),
            qtdDownload = 0,
            escola = "Escola Teste",
            duracaoEmMinutos = 60,
            titulo = "Título Teste",
            resumo = null,
            objetivoGeral = "Objetivo Geral Teste",
            objetivosEspecificos = "Objetivos Específicos Teste",
            metodologia = "Metodologia Teste",
            referencias = "Referências Teste",
            token = null,
            status = StatusPlanoAula.RASCUNHO,
            autor = usuario.toEntity(),
            nivelEnsino = nivelEnsino,
            avaliacao = "avaliação teste",
            disciplinasEnvolvidas = listOf(disciplina),
            anoEnsino = anoEnsino,
            objetosAprendizagem = setOf(),
            coautores = setOf()
        )

        Mockito.`when`(planoAulaRepository.save(any(PlanoAulaEntity::class.java))).thenReturn(planoAulaEntity)

        // Execution
        val resultado = planoAulaGatewayAdapter.salvarPlanoAula(
            usuario,
            "Escola Teste",
            1L,
            listOf(1L),
            1L,
            60,
            "Título Teste",
            "Metodologia Teste",
            "Objetivos Específicos Teste",
            "Objetivo Geral Teste",
            "avaliação teste",
            "Referências Teste"
        )

        // Verification
        Assertions.assertEquals("Escola Teste", resultado.getEscola())
        Assertions.assertEquals("Título Teste", resultado.getTitulo())
        Assertions.assertEquals(60, resultado.getDuracaoEmMinutos())
        Assertions.assertEquals("Objetivo Geral Teste", resultado.getObjetivoGeral())
        Assertions.assertNotNull(resultado.getNivelEnsino())
    }

    @Test
    fun `Deve lançar NivelEnsinoNaoEncontradoException`() {
        val usuario = criarUsuarioAtivo()

        Mockito.`when`(nivelEnsinoRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<NivelEnsinoNaoEncontradoException> {
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                "Escola Teste",
                1L,
                listOf(1L),
                1L,
                60,
                "Título Teste",
                "Metodologia Teste",
                "Objetivos Específicos Teste",
                "Objetivo Geral Teste",
                "avaliação teste",
                "Referências Teste"
            )
        }
    }

    @Test
    fun `Deve lançar AnoEnsinoNaoEncontradoException`() {
        val usuario = criarUsuarioAtivo()
        val nivelEnsino = NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF")

        Mockito.`when`(nivelEnsinoRepository.findById(1L)).thenReturn(Optional.of(nivelEnsino))
        Mockito.`when`(anoEnsinoRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<AnoEnsinoNaoEncontradoException> {
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                "Escola Teste",
                1L,
                listOf(1L),
                1L,
                60,
                "Título Teste",
                "Metodologia Teste",
                "Objetivos Específicos Teste",
                "Objetivo Geral Teste",
                "avaliação teste",
                "Referências Teste"
            )
        }
    }

    @Test
    fun `Deve lançar DisciplinaNaoEncontradoException`() {
        val usuario = criarUsuarioAtivo()
        val nivelEnsino = NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF")
        val anoEnsino = AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino)

        Mockito.`when`(nivelEnsinoRepository.findById(1L)).thenReturn(Optional.of(nivelEnsino))
        Mockito.`when`(anoEnsinoRepository.findById(1L)).thenReturn(Optional.of(anoEnsino))
        Mockito.`when`(disciplinaRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<DisciplinaNaoEncontradoException> {
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                "Escola Teste",
                1L,
                listOf(1L),
                1L,
                60,
                "Título Teste",
                "Metodologia Teste",
                "Objetivos Específicos Teste",
                "Objetivo Geral Teste",
                "avaliação teste",
                "Referências Teste"
            )
        }
    }

    @Test
    fun `Deve lançar PlanoAulaDuracaoNegativaException`() {
        val usuario = criarUsuarioAtivo()
        val nivelEnsino = NivelEnsinoEntity(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF")
        val anoEnsino = AnoEnsinoEntity(id = 1L, nome = "teste", nivelEnsino = nivelEnsino)
        val disciplina = DisciplinaEntity(id = 1L, nome = "Matemática")

        Mockito.`when`(nivelEnsinoRepository.findById(1L)).thenReturn(Optional.of(nivelEnsino))
        Mockito.`when`(anoEnsinoRepository.findById(1L)).thenReturn(Optional.of(anoEnsino))
        Mockito.`when`(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina))

        assertThrows<PlanoAulaDuracaoNegativaException> {
            planoAulaGatewayAdapter.salvarPlanoAula(
                usuario,
                "Escola Teste",
                1L,
                listOf(1L),
                1L,
                -10,
                "Título Teste",
                "Metodologia Teste",
                "Objetivos Específicos Teste",
                "Objetivo Geral Teste",
                "avaliação teste",
                "Referências Teste"
            )
        }
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

}
