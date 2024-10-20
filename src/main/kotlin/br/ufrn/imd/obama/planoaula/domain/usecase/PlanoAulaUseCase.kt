package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaUseCase {
    fun buscarPlanoAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>

    fun buscarPlanoAulaPorId(id: Long): PlanoAula

    fun buscarPlanosAulaPorCoautor(
        coautor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>
}
