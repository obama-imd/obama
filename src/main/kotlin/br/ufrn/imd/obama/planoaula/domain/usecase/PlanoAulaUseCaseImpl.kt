package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.model.Usuario

class PlanoAulaUseCaseImpl(
    private val planoAulaGateway: PlanoAulaGateway
): PlanoAulaUseCase {

    override fun criarPlanoAula(
        usuario: Usuario,
        planoAula: PlanoAulaRequest
    ): PlanoAula {
        return planoAulaGateway.salvarPlanoAula(usuario, planoAula)
    }
}
