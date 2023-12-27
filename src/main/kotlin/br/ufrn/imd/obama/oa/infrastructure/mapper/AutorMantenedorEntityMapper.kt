package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor
import br.ufrn.imd.obama.oa.infrastructure.entity.AutorMantenedorEntity

fun AutorMantenedorEntity.toModel(): AutorMantenedor {
    return AutorMantenedor(
        id = this.id,
        nome = this.nome,
        email = this.email,
        site = this.site
    )
}
