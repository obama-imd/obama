package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ListarAnoEnsinoResponse(
    var id: Long,

    var nome: String,

    @JsonProperty("nivel_ensino")
    var nivelEnsino: NivelEnsino
)

