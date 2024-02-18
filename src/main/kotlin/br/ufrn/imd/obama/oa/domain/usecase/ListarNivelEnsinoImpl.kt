package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino

class ListarNivelEnsinoImpl(
    private val nivelEnsinoDatabaseGateway: NivelEnsinoDatabaseGateway
): ListarNivelEnsino {

    override fun listarNiveisEnsino(): Set<NivelEnsino> {
        return nivelEnsinoDatabaseGateway.listarNivelEnsino()
    }
}
