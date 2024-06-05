package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class DisciplinaUseCaseImpl(
    private val disciplinaGateway: DisciplinaGateway
) : DisciplinaUseCase {

    override fun listarDisciplinas(
        pageable: Pageable
    ): Page<Disciplina> {
        return disciplinaGateway.listarDisciplinas(pageable)
    }

}
