package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

fun criarTemaConteudo(): TemaConteudo {
    return TemaConteudo(
        1L,
        "",
        criarDisciplina(),
        criarCurriculo()
    )
}
