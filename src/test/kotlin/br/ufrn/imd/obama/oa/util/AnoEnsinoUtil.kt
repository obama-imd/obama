package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino

fun criarAnoEnsino(): AnoEnsino {
    return AnoEnsino(
        1L,
        "",
        criarNivelEnsino()
    )
}
