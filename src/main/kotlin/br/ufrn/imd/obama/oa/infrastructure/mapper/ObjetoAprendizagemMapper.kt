package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse

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
