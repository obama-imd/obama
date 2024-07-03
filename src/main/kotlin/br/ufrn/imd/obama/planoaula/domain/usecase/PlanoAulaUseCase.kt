package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface PlanoAulaUseCase {

    fun criarPlanoAula(
        usuario: Usuario,
        planoAula: PlanoAulaRequest
    ): PlanoAula
}
