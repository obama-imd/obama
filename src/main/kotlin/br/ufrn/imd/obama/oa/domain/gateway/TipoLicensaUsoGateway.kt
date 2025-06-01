package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso

interface TipoLicensaUsoGateway {
    fun buscarPorId(id: Long): TipoLicensaUso?
}