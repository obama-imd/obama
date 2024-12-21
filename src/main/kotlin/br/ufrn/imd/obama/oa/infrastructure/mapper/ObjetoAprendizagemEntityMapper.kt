package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel

fun ObjetoAprendizagemEntity.toModel(): ObjetoAprendizagem {
    val oa = ObjetoAprendizagem(
        id = this.id,
        nome = this.nome,
        descricao =  this.descricao,
        quantidadeAcessos = this.quantidadeAcessos,
        thumbnailPath = this.thumbnailPath,
        dataLancamento = this.dataLancamento,
        versao = this.versao,
        ativo = this.ativo,
        tipoLicensaUso = this.tipoLicensaUso?.toModel(),
        idiomas = this.idiomas?.map { it.toModel() }?.toSet(),
        autoresMantenedores = this.autoresMantenedores.map { it.toModel() }.toSet(),
        descritores = this.descritores.map { it.toModel() }.toSet(),
        habilidades = this.habilidades.map { it.toModel() }.toSet(),
        plataformas = this.objetoAprendizagemPlataformas.map { it.toModel() }
    )

    oa.usuariosFavoritaram = this.usuariosFavoritaram.map { it.toModel() }.toMutableSet()

    return oa;
}
