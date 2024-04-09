package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarTemaConteudoResponse

fun TemaConteudo.toResponse(): ListarTemaConteudoResponse {
    return ListarTemaConteudoResponse(
        id = this.id,
        nome = this.nome
    )
}

fun TemaConteudo.toEntity(): TemaConteudoEntity {
    return TemaConteudoEntity(
        id = this.id,
        nome = this.nome,
        disciplina = this.disciplina.toEntity(),
        curriculo = this.curriculo.toEntity()
    )
}
