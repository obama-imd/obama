package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class PlanoAulaCompartilharRequest(
    val idPlanoAula: Long,
    val emailUsuarios: List<String>
)
