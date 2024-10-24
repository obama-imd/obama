package br.ufrn.imd.obama.planoaula.util

import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
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
    )
}

fun criarPlanoAulaComCoautores(): PlanoAula {
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
        setOf(criarUsuarioAtivo(), criarUsuarioAtivo())
    )
}
