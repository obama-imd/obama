package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Descritor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DescritorDatabaseGateway {
    fun listarDescritores(pageable: Pageable): Page<Descritor>
}
