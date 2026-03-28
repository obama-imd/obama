package br.ufrn.imd.obama.planoaula.domain.model

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class PlanoAulaTest {

    @Test
    fun `deve retornar todos os valores via getters`() {
        val planoAula = criarPlanoAulaCompleto()

        assertEquals(42L, planoAula.id)
        assertEquals(LocalDateTime.of(2026, 3, 28, 10, 0), planoAula.dataCadastro)
        assertEquals(12, planoAula.getQtdDownload())
        assertEquals("Escola Estadual Modelo", planoAula.getEscola())
        assertEquals(50, planoAula.getDuracaoEmMinutos())
        assertEquals("Plano de Álgebra", planoAula.getTitulo())
        assertEquals("Resumo do plano", planoAula.getResumo())
        assertEquals("Compreender expressões algébricas", planoAula.getObjetivoGeral())
        assertEquals("Resolver equações de 1º grau", planoAula.getObjetivosEspecificos())
        assertEquals("Aula expositiva dialogada", planoAula.getMetodologia())
        assertEquals("BNCC e livros didáticos", planoAula.getReferencias())
        assertEquals("token-plano-aula", planoAula.getToken())
        assertEquals(StatusPlanoAula.VALIDADO, planoAula.getStatus())
        assertEquals("autor@obama.ufrn.br", planoAula.getAutor()?.email)
        assertEquals("Ensino Fundamental", planoAula.getNivelEnsino()?.nome)
        assertEquals(1, planoAula.getDisciplinasEnvolvidas()?.size)
        assertEquals("6º Ano", planoAula.getAnoEnsino()?.nome)
        assertEquals(1, planoAula.getObjetosAprendizagem()?.size)
        assertEquals(1, planoAula.getCoautores()?.size)
        assertEquals("Autoavaliação e lista de exercícios", planoAula.getAvaliacao())
    }

    @Test
    fun `deve manter campos opcionais nulos quando nao informados`() {
        val planoAula = PlanoAula(
            id = 1L,
            dataCadastro = LocalDateTime.of(2026, 1, 1, 8, 0),
            escola = null,
            status = StatusPlanoAula.RASCUNHO
        )

        assertEquals(0, planoAula.getQtdDownload())
        assertEquals(null, planoAula.getEscola())
        assertEquals(null, planoAula.getDuracaoEmMinutos())
        assertEquals(null, planoAula.getTitulo())
        assertEquals(null, planoAula.getResumo())
        assertEquals(null, planoAula.getObjetivoGeral())
        assertEquals(null, planoAula.getObjetivosEspecificos())
        assertEquals(null, planoAula.getMetodologia())
        assertEquals(null, planoAula.getReferencias())
        assertEquals(null, planoAula.getToken())
        assertEquals(StatusPlanoAula.RASCUNHO, planoAula.getStatus())
        assertEquals(null, planoAula.getAutor())
        assertEquals(null, planoAula.getNivelEnsino())
        assertEquals(null, planoAula.getDisciplinasEnvolvidas())
        assertEquals(null, planoAula.getAnoEnsino())
        assertEquals(null, planoAula.getObjetosAprendizagem())
        assertEquals(null, planoAula.getCoautores())
        assertEquals(null, planoAula.getAvaliacao())
    }

    @Test
    fun `deve respeitar contrato de igualdade hashcode e toString da data class`() {
        val planoAulaA = criarPlanoAulaCompleto()
        val planoAulaComOutroId = criarPlanoAulaCompleto(id = 100L)

        assertEquals(planoAulaA, planoAulaA)
        assertNotEquals(planoAulaA, planoAulaComOutroId)
        assertEquals(planoAulaA.hashCode(), planoAulaA.hashCode())
        assertTrue(planoAulaA.toString().contains("PlanoAula"))
    }

    private fun criarPlanoAulaCompleto(id: Long = 42L): PlanoAula {
        val autor = criarUsuario(id = 10L, email = "autor@obama.ufrn.br")
        val coautor = criarUsuario(id = 11L, email = "coautor@obama.ufrn.br")
        val nivelEnsino = NivelEnsino(id = 1L, nome = "Ensino Fundamental", nomeAbreviado = "EF")
        val anoEnsino = AnoEnsino(id = 2L, nome = "6º Ano", nivelEnsino = nivelEnsino)
        val disciplinas = listOf(Disciplina(id = 3L, nome = "Matemática"))
        val objetosAprendizagem: Set<ObjetoAprendizagem> = setOf(criarObjetoAprendizagem())

        return PlanoAula(
            id = id,
            dataCadastro = LocalDateTime.of(2026, 3, 28, 10, 0),
            qtdDownload = 12,
            escola = "Escola Estadual Modelo",
            duracaoEmMinutos = 50,
            titulo = "Plano de Álgebra",
            resumo = "Resumo do plano",
            objetivoGeral = "Compreender expressões algébricas",
            objetivosEspecificos = "Resolver equações de 1º grau",
            metodologia = "Aula expositiva dialogada",
            referencias = "BNCC e livros didáticos",
            token = "token-plano-aula",
            status = StatusPlanoAula.VALIDADO,
            autor = autor,
            nivelEnsino = nivelEnsino,
            disciplinasEnvolvidas = disciplinas,
            anoEnsino = anoEnsino,
            objetosAprendizagem = objetosAprendizagem,
            coautores = setOf(coautor),
            avaliacao = "Autoavaliação e lista de exercícios"
        )
    }

    private fun criarUsuario(id: Long, email: String): Usuario {
        return Usuario(
            nome = "Usuário",
            sobrenome = "Teste",
            email = email,
            senha = "Senha@123",
            papel = Papel.PADRAO,
            ativo = true,
            tipoCadastro = TipoCadastro.PADRAO,
            token = "token-fixo-$id",
            usaCriptografiaAntiga = false
        ).apply {
            setId(id)
        }
    }
}
