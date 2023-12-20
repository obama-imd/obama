package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity

fun DisciplinaEntity.toModel() : Disciplina {
    return Disciplina(
            id = this.id,
            nome = this.nome
    )
}
