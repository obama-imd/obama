package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarNivelEnsinoResponse

fun NivelEnsino.toResponse(): ListarNivelEnsinoResponse {

    return ListarNivelEnsinoResponse(
        id = this.id,
        nome = this.nome
    )
}

fun NivelEnsino.toEntity(): NivelEnsinoEntity {
    return NivelEnsinoEntity(
        id = this.id,
        nome = this.nome,
        nomeAbreviado = this.nomeAbreviado
    )
}
