package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.infrastructure.entity.DescritorEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDescritorResponse

fun Descritor.toResponse(): ListarDescritorResponse {
    return ListarDescritorResponse(
        id = this.id,
        codigo = this.codigo,
        descricao = this.descricao,
        nomeNivelEnsino = this.nivelEnsino.nome,
        nomeTemaConteudo = this.temaConteudo.nome
    )
}

fun Descritor.toEntity(): DescritorEntity {
    return DescritorEntity(
        id = this.id,
        nivelEnsino = this.nivelEnsino.toEntity(),
        descricao = this.descricao,
        codigo = this.codigo,
        temaConteudo = this.temaConteudo.toEntity()
    )
}
