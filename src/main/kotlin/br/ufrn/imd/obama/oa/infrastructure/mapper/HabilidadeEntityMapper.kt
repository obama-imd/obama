package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Habilidade
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
