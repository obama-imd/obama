package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.Descritor

fun criarDescritor(): Descritor {
    return Descritor(
        1L,
        "Teste",
        "Teste",
        criarTemaConteudoBNCC(),
        criarNivelEnsino()
    )
}
