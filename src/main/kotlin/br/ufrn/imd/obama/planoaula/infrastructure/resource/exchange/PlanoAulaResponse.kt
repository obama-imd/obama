package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange

import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class PlanoAulaResponse (
    val id: Long,
    val titulo: String?,
    val status: StatusPlanoAula?
)
