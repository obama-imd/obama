package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.Habilidade

fun criarHabilidade(): Habilidade {
    return Habilidade(
        1L,
        "Teste",
        "Teste",
        "Teste",
        criarTemaConteudo(),
        criarAnoEnsino()
    )
}
