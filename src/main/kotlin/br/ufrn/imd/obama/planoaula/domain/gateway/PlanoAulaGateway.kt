package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaGateway {
    fun buscarPlanosAulaPorTitulo(titulo: String?, pageable: Pageable): Page<PlanoAula>
}
