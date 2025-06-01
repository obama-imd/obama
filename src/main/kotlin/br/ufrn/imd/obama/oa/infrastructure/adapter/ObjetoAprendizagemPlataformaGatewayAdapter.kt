package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemPlataformaGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemPlataformaRepository
import org.springframework.stereotype.Service

@Service
class ObjetoAprendizagemPlataformaGatewayAdapter(
    private val objetoAprendizagemPlataformaRepository: ObjetoAprendizagemPlataformaRepository
) : ObjetoAprendizagemPlataformaGateway {
    override fun buscarPorId(id: Long): ObjetoAprendizagemPlataforma? {
        return objetoAprendizagemPlataformaRepository.findById(id).orElse(null)?.toModel()
    }
}
