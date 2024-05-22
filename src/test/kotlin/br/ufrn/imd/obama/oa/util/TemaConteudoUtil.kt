package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

fun criarTemaConteudoBNCC(): TemaConteudo {
    return TemaConteudo(
        1L,
        "",
        criarDisciplina(),
        Curriculo.BNCC
    )
}

fun criarTemaConteudoPCN(): TemaConteudo {
    return TemaConteudo(
        1L,
        "",
        criarDisciplina(),
        Curriculo.PCN
    )
}
