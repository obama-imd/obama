package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class PlanoAulaUseCaseImpl(
    private val planoAulaGateway: PlanoAulaGateway
): PlanoAulaUseCase {
    override fun buscarPlanoAulaPorTitulo(titulo: String?, pageable: Pageable): Page<PlanoAula> {
        return planoAulaGateway.buscarPlanosAulaPorTitulo(titulo, pageable)
    }
}
