package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy::class)
class ListarDescritorResponse(
    val id: Long,
    val descricao: String,
    val codigo: String,
    @JsonProperty("nome_tema_conteudo")
    val nomeTemaConteudo: String,
    @JsonProperty("nome_nivel_ensino")
    val nomeNivelEnsino: String
)
