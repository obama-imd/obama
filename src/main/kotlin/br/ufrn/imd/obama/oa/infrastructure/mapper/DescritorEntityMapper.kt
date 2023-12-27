package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.infrastructure.entity.DescritorEntity

fun DescritorEntity.toModel(): Descritor {
    return Descritor(
        id = this.id,
        descricao = this.descricao,
        temaConteudo = this.temaConteudo.toModel(),
        nivelEnsino = this.nivelEnsino.toModel(),
        codigo = this.codigo
    )
}
