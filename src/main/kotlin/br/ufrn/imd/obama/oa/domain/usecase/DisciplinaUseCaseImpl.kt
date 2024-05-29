package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse

class DisciplinaUseCaseImpl(
    private val disciplinaGateway: DisciplinaGateway
) : DisciplinaUseCase {

    override fun listarDisciplinas(): List<Disciplina> {
        return disciplinaGateway.listarDisciplinas()
    }

}
