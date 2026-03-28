package br.ufrn.imd.obama.oa.domain.model

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OaDomainModelTest {

    @Test
    fun `deve criar modelos simples com os valores informados`() {
        val autorMantenedor = AutorMantenedor(
            id = 1L,
            nome = "Autor",
            email = "autor@obama.ufrn.br",
            site = "https://obama.ufrn.br"
        )
        val idioma = Idioma(id = 2L, nome = "Português")
        val disciplina = Disciplina(id = 3L, nome = "Matemática")
        val plataforma = Plataforma(id = 4L, nome = "Web")
        val nivelEnsino = NivelEnsino(nome = "Ensino Médio", nomeAbreviado = "EM")
        val anoEnsino = AnoEnsino(id = 5L, nome = "1º Ano", nivelEnsino = nivelEnsino)
        val temaConteudo = TemaConteudo(
            id = 6L,
            nome = "Álgebra",
            disciplina = disciplina,
            curriculo = Curriculo.BNCC
        )
        val descritor = Descritor(
            id = 7L,
            descricao = "Resolver problemas com equações",
            codigo = "D1",
            temaConteudo = temaConteudo,
            nivelEnsino = nivelEnsino
        )
        val habilidade = Habilidade(
            id = 8L,
            descricao = "Interpretar expressões algébricas",
            conhecimentos = "Álgebra básica",
            codigo = "H1",
            temaConteudo = temaConteudo,
            anoEnsino = anoEnsino
        )
        val tipoLicensaUso = TipoLicensaUso(
            id = 9L,
            nome = "Creative Commons",
            versao = "4.0"
        )

        assertEquals(1L, autorMantenedor.id)
        assertEquals("Português", idioma.nome)
        assertEquals("Matemática", disciplina.nome)
        assertEquals("Web", plataforma.nome)
        assertEquals(0L, nivelEnsino.id)
        assertEquals("EM", nivelEnsino.nomeAbreviado)
        assertEquals(nivelEnsino, anoEnsino.nivelEnsino)
        assertEquals(Curriculo.BNCC, temaConteudo.curriculo)
        assertEquals("D1", descritor.codigo)
        assertEquals("H1", habilidade.codigo)
        assertEquals("Creative Commons", tipoLicensaUso.nome)
    }

    @Test
    fun `deve permitir atualizar dados mutaveis de tipo licensa uso`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L,
            nome = "CC",
            versao = "3.0"
        )

        tipoLicensaUso.nome = "GPL"
        tipoLicensaUso.versao = "2.0"

        assertEquals("GPL", tipoLicensaUso.nome)
        assertEquals("2.0", tipoLicensaUso.versao)
    }

    @Test
    fun `deve expor descricoes corretas do tipo acesso`() {
        assertEquals("Smartphone/Tablet", TipoAcesso.DISPOSITIVO_MOVEL.description)
        assertEquals("Web Browser", TipoAcesso.WEB.description)
        assertEquals("Download", TipoAcesso.EXECUTAVEL.description)
    }

    @Test
    fun `deve criar objeto aprendizagem plataforma`() {
        val plataforma = Plataforma(id = 1L, nome = "Plataforma Web")
        val oaPlataforma = ObjetoAprendizagemPlataforma(
            tipoAcesso = TipoAcesso.WEB,
            link = "https://obama.ufrn.br/oa/1",
            plataforma = plataforma
        )

        assertEquals(TipoAcesso.WEB, oaPlataforma.tipoAcesso)
        assertEquals("https://obama.ufrn.br/oa/1", oaPlataforma.link)
        assertEquals(plataforma, oaPlataforma.plataforma)
    }

    @Test
    fun `deve considerar iguais objetos de aprendizagem com mesmo id`() {
        val primeiro = criarObjetoAprendizagem(id = 10L)
        val segundo = criarObjetoAprendizagem(id = 10L)

        assertTrue(primeiro.equals(segundo))
    }

    @Test
    fun `deve considerar diferentes objetos de aprendizagem com ids distintos`() {
        val primeiro = criarObjetoAprendizagem(id = 10L)
        val segundo = criarObjetoAprendizagem(id = 11L)

        assertFalse(primeiro.equals(segundo))
    }

    @Test
    fun `deve lancar excecao ao comparar objeto aprendizagem com tipo invalido`() {
        val objetoAprendizagem = criarObjetoAprendizagem(id = 10L)

        assertThrows(ClassCastException::class.java) {
            objetoAprendizagem.equals("tipo-invalido")
        }
    }

    private fun criarObjetoAprendizagem(id: Long): ObjetoAprendizagem {
        val disciplina = Disciplina(id = 1L, nome = "Matemática")
        val nivelEnsino = NivelEnsino(id = 1L, nome = "Ensino Médio", nomeAbreviado = "EM")
        val anoEnsino = AnoEnsino(id = 1L, nome = "1º Ano", nivelEnsino = nivelEnsino)
        val temaConteudo = TemaConteudo(
            id = 1L,
            nome = "Álgebra",
            disciplina = disciplina,
            curriculo = Curriculo.BNCC
        )
        val descritor = Descritor(
            id = 1L,
            descricao = "Descritor",
            codigo = "D1",
            temaConteudo = temaConteudo,
            nivelEnsino = nivelEnsino
        )
        val habilidade = Habilidade(
            id = 1L,
            descricao = "Habilidade",
            conhecimentos = "Conhecimentos",
            codigo = "H1",
            temaConteudo = temaConteudo,
            anoEnsino = anoEnsino
        )
        val plataforma = ObjetoAprendizagemPlataforma(
            tipoAcesso = TipoAcesso.WEB,
            link = "https://obama.ufrn.br",
            plataforma = Plataforma(id = 1L, nome = "Web")
        )

        return ObjetoAprendizagem(
            id = id,
            nome = "OA",
            descricao = "Objeto de aprendizagem",
            quantidadeAcessos = 0,
            thumbnailPath = null,
            dataLancamento = LocalDate.of(2025, 1, 1),
            versao = "1.0.0",
            ativo = true,
            tipoLicensaUso = TipoLicensaUso(id = 1L, nome = "CC", versao = "4.0"),
            idiomas = setOf(Idioma(id = 1L, nome = "Português")),
            autoresMantenedores = setOf(
                AutorMantenedor(
                    id = 1L,
                    nome = "Autor",
                    email = "autor@obama.ufrn.br",
                    site = "https://obama.ufrn.br"
                )
            ),
            descritores = setOf(descritor),
            habilidades = setOf(habilidade),
            plataformas = listOf(plataforma)
        )
    }
}
