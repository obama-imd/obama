package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)
