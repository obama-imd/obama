package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaUseCase {
    fun buscarPlanoAulaPorTitulo(titulo: String?, pageable: Pageable): Page<PlanoAula>
}
