package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class DisciplinaGatewayAdapter(
    private val disciplinaRepository: DisciplinaRepository
): DisciplinaGateway {

    override fun listarDisciplinas(): List<Disciplina> {
        return disciplinaRepository.findAll(Sort.by("nome")).map { it.toModel() }
    }

}
