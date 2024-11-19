package br.ufrn.imd.obama.planoaula.util

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import java.time.LocalDateTime
import java.util.*

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
        setOf(criarUsuarioAtivo(2L))
    )
}

fun criarPlanoAula(
    id: Long,
    autor: Usuario? = null,
    nivelEnsino: NivelEnsino? = null,
    disciplinas: List<Disciplina>? = null,
    anoEnsino: AnoEnsino? = null,
    objetosAprendizagem: Set<ObjetoAprendizagem>? = null,
    coautores: Set<Usuario>?
): PlanoAula {
    return PlanoAula(
        id,
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
        UUID.randomUUID().toString(),
        StatusPlanoAula.VALIDADO,
        autor,
        nivelEnsino,
        disciplinas,
        anoEnsino,
        objetosAprendizagem,
        coautores
    )
}