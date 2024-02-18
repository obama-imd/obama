package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino

interface NivelEnsinoDatabaseGateway {

    fun listarNivelEnsino(): Set<NivelEnsino>
}
