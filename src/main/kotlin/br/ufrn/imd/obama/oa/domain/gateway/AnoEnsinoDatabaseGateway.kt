package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AnoEnsinoDatabaseGateway {
    fun listarAnosEnsino (pageable: Pageable) : Page<AnoEnsino>
}
