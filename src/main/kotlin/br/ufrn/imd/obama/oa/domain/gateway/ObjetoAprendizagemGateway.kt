package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ObjetoAprendizagemGateway {

    fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem
}
