package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import br.ufrn.imd.obama.usuario.infrastructure.annotation.ValidPassword
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CadastrarUsuarioRequest(
    @field:NotBlank(message = "Campo obrigatório")
    val nome: String,

    @field:NotBlank(message = "Campo obrigatório")
    val sobrenome: String,

    @field:Email(message = "Email inválido")
    val email: String,

    @field:Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    @field:ValidPassword(message = "A senha não atende aos critérios de segurança")
    val senha: String,
)
