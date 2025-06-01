package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.TipoLicensaUsoGateway
import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.TipoLicensaUsoRepository
import org.springframework.stereotype.Service

@Service
class TipoLicensaUsoGatewayAdapter(
    private val tipoLicensaUsoRepository: TipoLicensaUsoRepository
) : TipoLicensaUsoGateway {
    override fun buscarPorId(id: Long): TipoLicensaUso? {
        return tipoLicensaUsoRepository.findById(id).orElse(null)?.toModel()
    }

}
