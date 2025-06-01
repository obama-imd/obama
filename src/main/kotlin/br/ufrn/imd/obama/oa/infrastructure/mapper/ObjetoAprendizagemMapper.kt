package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ObjetoAprendizagemResponse

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
    return ObjetoAprendizagemEntity(
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
}

fun ObjetoAprendizagem.toResponse(): ObjetoAprendizagemResponse {
    return ObjetoAprendizagemResponse(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        quantidadeAcessos = this.quantidadeAcessos,
        thumbnailPath = this.thumbnailPath,
        dataLancamento = this.dataLancamento,
        versao = this.versao,
        ativo = this.ativo,
        tipoLicensaUsoId = this.tipoLicensaUso?.id ?: 0L,
        idiomaIds = this.idiomas?.map { it.id }?.toSet() ?: emptySet(),
        autorMantenedorIds = this.autoresMantenedores.map { it.id }.toSet(),
        descritorIds = this.descritores.map { it.id }.toSet(),
        habilidadeIds = this.habilidades.map { it.id }.toSet(),
        plataformaIds = this.plataformas.map { it.plataforma.id }
    )
}
