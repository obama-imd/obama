package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino

class NivelEnsinoUseCaseImpl(
    private val nivelEnsinoDatabaseGateway: NivelEnsinoDatabaseGateway
): br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCase {

    override fun listarNiveisEnsino(): Set<NivelEnsino> {
        return nivelEnsinoDatabaseGateway.listarNivelEnsino()
    }
}
