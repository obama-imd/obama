package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import br.ufrn.imd.obama.usuario.domain.enums.Role
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy::class)
class UsuarioResponse (
    val id: Long,
    val nome: String,
    val sobrenome: String,
    val email: String,
    val role: Role,
    val ativo: Boolean,
    @JsonProperty("tipo_cadastro")
    val tipoCadastro: TipoCadastro,
    val token: String,
)
