package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface PlanoAulaGateway {

    fun salvarPlanoAula(
        usuario: Usuario,
        request: PlanoAulaRequest
    ): PlanoAula
}
