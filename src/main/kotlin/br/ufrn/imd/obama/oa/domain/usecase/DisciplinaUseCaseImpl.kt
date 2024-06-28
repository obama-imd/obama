package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class DisciplinaUseCaseImpl(
    private val disciplinaGateway: DisciplinaDatabaseGateway
) : DisciplinaUseCase {

    override fun listarDisciplinas(
        pageable: Pageable
    ): Page<Disciplina> {
        return disciplinaGateway.listarDisciplinas(pageable)
    }

}
