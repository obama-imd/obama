package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CadastrarUsuarioRequest(
    @NotBlank
    val nome: String,

    @NotBlank
    val sobrenome: String,

    @Email
    val email: String,
    @Size(min=8)
    val senha: String,
)
