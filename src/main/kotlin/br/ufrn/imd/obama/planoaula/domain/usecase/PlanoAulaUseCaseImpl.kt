package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class PlanoAulaUseCaseImpl(
    private val planoAulaGateway: PlanoAulaGateway
): PlanoAulaUseCase {
    override fun buscarPlanoAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula> {
        return planoAulaGateway.buscarPlanosAulaPorTitulo(autor,titulo,pageable)
    }
}
