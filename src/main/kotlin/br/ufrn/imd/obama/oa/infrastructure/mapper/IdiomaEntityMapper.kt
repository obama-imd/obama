package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Idioma
import br.ufrn.imd.obama.oa.infrastructure.entity.IdiomaEntity

fun IdiomaEntity.toModel(): Idioma {
    return Idioma(
        id = this.id,
        nome = this.nome
    )
}
