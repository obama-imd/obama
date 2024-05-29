package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Disciplina

interface DisciplinaGateway {

    fun listarDisciplinas(): List<Disciplina>

}
