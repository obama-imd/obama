package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.AnoEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class AnoEnsinoUseCaseImpl(
    private val anoEnsinoDatabaseGateway: AnoEnsinoDatabaseGateway
) : AnoEnsinoUseCase {

    override fun listarAnosEnsino(pageable: Pageable): Page<AnoEnsino> {
        return anoEnsinoDatabaseGateway.listarAnosEnsino(pageable)
    }
}
