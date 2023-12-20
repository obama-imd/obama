package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse

fun ObjetoAprendizagem.toBuscarOaResponse(): BuscarOaResponse {
    return BuscarOaResponse(
            id = this.id,
            thumbnailPath = this.thumbnailPath,
            nome = this.nome
    )
}
