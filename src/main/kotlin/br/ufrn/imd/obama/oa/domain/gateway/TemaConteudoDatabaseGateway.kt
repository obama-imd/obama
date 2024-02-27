package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

interface TemaConteudoDatabaseGateway {

    fun listarTemaConteudo(): Set<TemaConteudo>
}
