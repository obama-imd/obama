package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.DescritorDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.DescritorRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class DescritorDatabaseGatewayAdapter(
    private val descritorRepository: DescritorRepository
): DescritorDatabaseGateway {
    override fun listarDescritores(pageable: Pageable): Page<Descritor> {
        return descritorRepository.findAllByOrderByCodigoAsc(pageable).map { it.toModel() }
    }
}
