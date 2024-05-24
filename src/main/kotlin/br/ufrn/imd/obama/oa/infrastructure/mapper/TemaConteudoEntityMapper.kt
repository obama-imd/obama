package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity

fun TemaConteudoEntity.toModel(): TemaConteudo {
    return TemaConteudo(
            id = this.id,
            nome = this.nome,
            disciplina = this.disciplina.toModel(),
            curriculo = this.curriculo
    )
}
