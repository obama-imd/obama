package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Plataforma
import br.ufrn.imd.obama.oa.infrastructure.entity.PlataformaEntity

fun PlataformaEntity.toModel(): Plataforma {
    return Plataforma(
            id = this.id,
            nome = this.nome
    )
}
