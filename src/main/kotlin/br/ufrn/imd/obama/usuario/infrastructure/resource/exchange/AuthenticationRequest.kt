package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(
    @NotBlank
    val login: String,

    @NotBlank
    val senha: String
) {

}
