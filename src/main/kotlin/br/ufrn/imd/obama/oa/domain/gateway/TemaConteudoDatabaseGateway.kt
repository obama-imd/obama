package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

interface TemaConteudoDatabaseGateway {

    fun listarTemaConteudo(curriculo: Curriculo): Set<TemaConteudo>
}
