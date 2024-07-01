package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest

interface PlanoAulaGateway {

    fun salvarPlanoAula(
        token: String,
        request: PlanoAulaRequest
    ): PlanoAula
}
