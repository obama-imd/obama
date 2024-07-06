package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DisciplinaUseCase {

    fun listarDisciplinas(
        pageable: Pageable
    ): Page<Disciplina>
}
