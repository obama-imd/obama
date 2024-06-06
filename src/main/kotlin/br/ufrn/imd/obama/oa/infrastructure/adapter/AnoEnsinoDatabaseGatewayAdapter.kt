package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.AnoEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnoEnsinoDatabaseGatewayAdapter(
    private val anoEnsinoRepository: AnoEnsinoRepository
) : AnoEnsinoDatabaseGateway {

    override fun listarAnosEnsino(pageable: Pageable): Page<AnoEnsino> {
        return anoEnsinoRepository.findAll(pageable).map { it.toModel() }
    }
}
