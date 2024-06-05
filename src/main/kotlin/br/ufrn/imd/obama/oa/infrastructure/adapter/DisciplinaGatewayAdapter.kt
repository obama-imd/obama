package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class DisciplinaGatewayAdapter(
    private val disciplinaRepository: DisciplinaRepository
): DisciplinaGateway {

    override fun listarDisciplinas(
        pageable: Pageable
    ): Page<Disciplina> {
        return disciplinaRepository.findAllByOrderByNomeAsc(pageable).map { it.toModel() }
    }

}
