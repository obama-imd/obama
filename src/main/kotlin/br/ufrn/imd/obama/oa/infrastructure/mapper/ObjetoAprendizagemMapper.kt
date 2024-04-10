package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse

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
