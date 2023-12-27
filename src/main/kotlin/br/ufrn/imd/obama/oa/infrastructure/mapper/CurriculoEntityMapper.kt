package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Curriculo
import br.ufrn.imd.obama.oa.infrastructure.entity.CurriculoEntity

fun CurriculoEntity.toModel() : Curriculo {
    return Curriculo(
            id = this.id,
            nomeAbreviado = this.nomeAbreviado,
            nomeCompleto = this.nomeCompleto
    )
}
