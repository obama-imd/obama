package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem


interface ObjetoAprendizagemGateway {

    fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem
}
