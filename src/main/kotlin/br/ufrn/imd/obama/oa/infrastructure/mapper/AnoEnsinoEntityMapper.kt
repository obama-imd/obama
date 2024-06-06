package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarAnoEnsinoResponse

fun AnoEnsinoEntity.toModel(): AnoEnsino {
    return AnoEnsino(
            id = this.id,
            nome = this.nome,
            nivelEnsino = this.nivelEnsino.toModel()
    )
}

fun AnoEnsino.toEntity(): AnoEnsinoEntity {
    return AnoEnsinoEntity(
        id = this.id,
        nome = this.nome,
        nivelEnsino = this.nivelEnsino.toEntity()
    )
}

fun AnoEnsino.toResponse(): ListarAnoEnsinoResponse {
    return ListarAnoEnsinoResponse(
        id = this.id,
        nome = this.nome,
        nivelEnsino = this.nivelEnsino
    )
}
