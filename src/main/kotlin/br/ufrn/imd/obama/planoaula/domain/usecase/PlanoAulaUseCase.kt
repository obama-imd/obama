package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaUseCase {

    fun criarPlanoAula(
        usuario: Usuario,
        planoAula: PlanoAulaRequest
    ): PlanoAula

    fun buscarPlanoAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>

}
