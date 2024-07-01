package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest

class PlanoAulaUseCaseImpl(
    private val planoAulaGateway: PlanoAulaGateway
): PlanoAulaUseCase {

    override fun criarPlanoAula(
        token: String,
        planoAula: PlanoAulaRequest
    ): PlanoAula {
        return planoAulaGateway.salvarPlanoAula(token, planoAula)
    }
}
