package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class PlanoAulaAssociarOARequest(val planoId: Long, val objetoAprendizagemIds: Set<Long>) {
    operator fun component1(): Long {
        return planoId;
    }

    operator fun component2(): Set<Long> {
        return objetoAprendizagemIds;
    }
}

