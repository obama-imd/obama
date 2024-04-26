package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy::class)
class BuscarOaResponse(
    val id: Long,

    val nome: String,

    @JsonProperty("caminho_imagem")
    val caminhoImagem: String?,
)
