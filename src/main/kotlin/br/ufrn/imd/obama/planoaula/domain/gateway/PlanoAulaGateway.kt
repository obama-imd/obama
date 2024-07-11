package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaGateway {
    fun buscarPlanosAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>
}
