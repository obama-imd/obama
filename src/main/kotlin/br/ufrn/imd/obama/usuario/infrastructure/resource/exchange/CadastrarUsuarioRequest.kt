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

    @field:Email(message = "Email inválido")
    val email: String,

    @field:Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    val senha: String,
)
