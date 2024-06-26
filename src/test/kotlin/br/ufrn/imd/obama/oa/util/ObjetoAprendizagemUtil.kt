package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import java.time.LocalDate

fun criarObjetoAprendizagem(): ObjetoAprendizagem {
    return ObjetoAprendizagem(
        1L,
        "Objeto teste",
        "Objeto de aprendizagem para teste",
        2,
        null,
        LocalDate.now(),
        null,
        true,
        null,
        null,
        setOf(
            criarAutorMantenedor()
        ),
        setOf(
            criarDescritor()
        ),
        setOf(
            criarHabilidade()
        ),
        listOf(
            criarOAPlataforma()
        )
    )
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
