package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDisciplinaResponse

fun Disciplina.toEntity(): DisciplinaEntity {
    return DisciplinaEntity(
        id=this.id,
        nome = this.nome
    )
}

fun Disciplina.toResponse(): ListarDisciplinaResponse {
    return ListarDisciplinaResponse(
        id = this.id,
        nome = this.nome
    )
}
