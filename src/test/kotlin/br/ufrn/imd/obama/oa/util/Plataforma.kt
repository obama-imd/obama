package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.domain.model.Plataforma
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso

fun criarOAPlataforma(): ObjetoAprendizagemPlataforma {
    return ObjetoAprendizagemPlataforma(
        TipoAcesso.WEB,
        "link",
        criarPlataforma()
    )
}

fun criarPlataforma(): Plataforma {
    return Plataforma(
        1L,
        "Teste"
    )
}
