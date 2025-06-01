package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.IdiomaGateway
import br.ufrn.imd.obama.oa.domain.model.Idioma
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.IdiomaRepository
import org.springframework.stereotype.Service

@Service
class IdiomaGatewayAdapter(
    private val idiomareRepository: IdiomaRepository
): IdiomaGateway {
    override fun buscarPorId(id: Long): Idioma? {
        return idiomareRepository.findById(id).orElse(null)?.toModel()
    }
}
