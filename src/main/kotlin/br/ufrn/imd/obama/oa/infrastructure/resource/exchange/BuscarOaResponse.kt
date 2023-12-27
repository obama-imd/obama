package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class BuscarOaResponse(
    val id: Long,

    val nome: String,

    val thumbnailPath: String?,
)
