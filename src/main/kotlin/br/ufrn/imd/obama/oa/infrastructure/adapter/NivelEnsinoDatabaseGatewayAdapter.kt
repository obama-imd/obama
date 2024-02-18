package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import java.util.stream.Collectors

class NivelEnsinoDatabaseGatewayAdapter(
    private val nivelEnsinoRepository: NivelEnsinoRepository
): NivelEnsinoDatabaseGateway {

    override fun listarNivelEnsino(): Set<NivelEnsino> {
        val stream = nivelEnsinoRepository.findAll().stream().map {
            it.toModel()
        }

        return stream.collect(Collectors.toSet());
    }
}
