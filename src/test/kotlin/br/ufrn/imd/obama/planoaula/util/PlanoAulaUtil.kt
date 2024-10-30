package br.ufrn.imd.obama.planoaula.util

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import java.time.LocalDateTime

fun criarPlanoAula(): PlanoAula {
    return PlanoAula(
        1L,
        LocalDateTime.now(),
        0,
        null,
        null,
        "teste",
        null,
        null,
        null,
        null,
        null,
        null,
        StatusPlanoAula.VALIDADO,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}

fun criarPlanoAulaComStatusRemovido(): PlanoAula {
    return PlanoAula(
        1L,
        LocalDateTime.now(),
        0,
        null,
        null,
        "teste",
        null,
        null,
        null,
        null,
        null,
        null,
        StatusPlanoAula.REMOVIDO,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}

fun criarPlanoAulaComAnoEnsinoComIdUm(): PlanoAula {
    return PlanoAula(
        1L,
        LocalDateTime.now(),
        0,
        "Escola Teste",
        null,
        "teste",
        null,
        null,
        null,
        null,
        null,
        null,
        StatusPlanoAula.VALIDADO,
        null,
        null,
        null,
        AnoEnsino(1L, "1º Ano", NivelEnsino(1L, "Ensino Fundamental", "EF")),
        null,
        null,
        null,
    )
}
