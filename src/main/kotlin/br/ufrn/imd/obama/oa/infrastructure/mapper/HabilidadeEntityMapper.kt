package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.domain.model.HabilidadeV2
import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity

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

fun HabilidadeEntity.toModelV2(): HabilidadeV2 {
    return HabilidadeV2(
        id = this.id,
        codigo = this.codigo,
        descricao = this.descricao,
        nomeAnoEnsino = this.anoEnsino.nome,
    )
}