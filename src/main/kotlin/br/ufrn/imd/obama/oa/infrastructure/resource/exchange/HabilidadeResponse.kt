package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class HabilidadeResponse(
    val id: Long,
    val codigo: String,
    val descricao: String,
    @JsonProperty("nome-ano-ensino")
    val nomeAnoEnsino: String
)