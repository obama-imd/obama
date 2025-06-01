package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.builder.ObjetoAprendizagemBuilder
import java.time.LocalDate

fun criarObjetoAprendizagem(): ObjetoAprendizagem {
    return ObjetoAprendizagemBuilder(
        id = 1L,
        nome = "Objeto teste",
        descricao = "Objeto de aprendizagem para teste",
        quantidadeAcessos = 2,
        ativo = true,
        autoresMantenedores = setOf(criarAutorMantenedor()),
        descritores = setOf(criarDescritor()),
        habilidades = setOf(criarHabilidade()),
        plataformas = listOf(criarOAPlataforma())
    )
        .thumbnailPath(null)
        .dataLancamento(LocalDate.now())
        .versao(null)
        .tipoLicensaUso(null)
        .idiomas(null)
        .build()
}

fun descritor(): Descritor {
    return Descritor(
        id=1L,
        descricao="D0",
        codigo="DO",
        temaConteudo = criarTemaConteudoBNCC(),
        nivelEnsino = criarNivelEnsino()
    )
}
