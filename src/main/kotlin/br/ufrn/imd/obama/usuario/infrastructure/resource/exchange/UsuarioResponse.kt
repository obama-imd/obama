package br.ufrn.imd.obama.usuario.infrastructure.resource.exchange

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy::class)
class UsuarioResponse (
    val nome: String,
    val sobrenome: String,
    val email: String,
)
