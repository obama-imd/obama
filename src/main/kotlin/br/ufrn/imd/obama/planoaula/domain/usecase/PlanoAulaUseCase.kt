package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest

interface PlanoAulaUseCase {

    fun criarPlanoAula(
        token: String,
        planoAula: PlanoAulaRequest
    ): PlanoAula
}
