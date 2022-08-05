package br.ufrn.imd.obama.extension

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.response.AcessoResponse
import br.ufrn.imd.obama.response.BuscaObjetoAprendizagemResponse

fun ObjetoAprendizagem.toBuscaObjetoAprendizagemResponse(): BuscaObjetoAprendizagemResponse {
    return BuscaObjetoAprendizagemResponse(
        id = this.id,
        nome = this.nome,
        pathArquivo = this.pathArquivo,
        acesso = objetoAprendizagemPlataformas.map {
            AcessoResponse(it.link, it.tipoVisualizacao.toString(), it.plataforma.nome)
        }
    )
}
