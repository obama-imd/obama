package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Disciplina

interface DisciplinaUseCase {

    fun listarDisciplinas(): List<Disciplina>
}
