package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.AutorMantenedorGateway
import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor
import br.ufrn.imd.obama.oa.infrastructure.repository.AutorMantenedorRepository
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import org.springframework.stereotype.Service

@Service
class AutorMantenedorGatewayAdapter(
    private val autorMantenedorRepository: AutorMantenedorRepository
) : AutorMantenedorGateway {
    override fun buscarPorId(id: Long): AutorMantenedor? {
        return autorMantenedorRepository.findById(id).orElse(null)?.toModel()
    }
}
