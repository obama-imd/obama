package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshResponse(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("refresh_token")
    val refreshToken: String
)
