package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity

fun NivelEnsinoEntity.toModel(): NivelEnsino {
    return NivelEnsino(
            id = this.id,
            nome = this.nome,
            nomeAbreviado = this.nomeAbreviado
    )
}
