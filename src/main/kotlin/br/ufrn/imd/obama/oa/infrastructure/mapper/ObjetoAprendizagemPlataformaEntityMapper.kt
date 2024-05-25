package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemPlataformaEntity

fun ObjetoAprendizagemPlataformaEntity.toModel(): ObjetoAprendizagemPlataforma {
    return ObjetoAprendizagemPlataforma(
        tipoAcesso = this.tipoAcesso,
        link = this.link,
        plataforma = this.plataforma.toModel()
    )
}
