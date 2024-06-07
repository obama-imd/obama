package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "Campo obrigatório")
    val login: String,

    @field:NotBlank(message = "Campo obrigatório")
    val senha: String
)
