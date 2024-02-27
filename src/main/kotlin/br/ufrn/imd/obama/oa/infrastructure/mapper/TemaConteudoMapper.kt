package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarTemaConteudoResponse

fun TemaConteudo.toResponse(): ListarTemaConteudoResponse {
    return ListarTemaConteudoResponse(
        id = this.id,
        nome = this.nome
    )
}
