package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity

fun ObjetoAprendizagem.toBuscarOaIdResponse(): BuscarOaIdResponse {
    return BuscarOaIdResponse(
        nome = this.nome,
        descricao = this.descricao,
        dataLancamento = this.dataLancamento,
        autoresMantenedores = this.autoresMantenedores.toList(),
        descritores = this.descritores.toList(),
        habilidades = this.habilidades.toList(),
        acessos = this.plataformas
    )
}

fun ObjetoAprendizagem.toBuscarOaResponse(): BuscarOaResponse {
    return BuscarOaResponse(
            id = this.id,
            caminhoImagem = this.thumbnailPath,
            nome = this.nome
    )
}

fun ObjetoAprendizagem.toEntity(): ObjetoAprendizagemEntity {
    var oa = ObjetoAprendizagemEntity(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        quantidadeAcessos = this.quantidadeAcessos,
        thumbnailPath = this.thumbnailPath,
        dataLancamento = this.dataLancamento,
        versao = this.versao,
        ativo = this.ativo,
        tipoLicensaUso = this.tipoLicensaUso?.toEntity()
    )

    oa.usuariosFavoritaram = this.usuariosFavoritaram.map { it.toEntity() }.toMutableSet()

    return oa
}
