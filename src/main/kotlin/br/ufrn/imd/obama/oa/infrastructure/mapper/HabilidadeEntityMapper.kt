package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.HabilidadeResponse

fun HabilidadeEntity.toModel(): Habilidade {
    return Habilidade(
            id = this.id,
            descricao = this.descricao,
            conhecimentos = this.conhecimentos,
            codigo = this.codigo,
            temaConteudo = this.temaConteudo.toModel(),
            anoEnsino = this.anoEnsino.toModel()
    )
}

fun Habilidade.toEntity(): HabilidadeEntity {
    return HabilidadeEntity(
        id = this.id,
        descricao = this.descricao,
        conhecimentos = this.conhecimentos,
        codigo = this.codigo,
        temaConteudo = this.temaConteudo.toEntity(),
        anoEnsino =  this.anoEnsino.toEntity()
    )
}

fun Habilidade.toResponse(): HabilidadeResponse {
    return HabilidadeResponse(
        id = this.id,
        codigo = this.codigo,
        descricao = this.descricao,
        nomeAnoEnsino = this.anoEnsino.nome
    )
}