package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DisciplinaGateway {

    fun listarDisciplinas(pageable: Pageable): Page<Disciplina>

}
