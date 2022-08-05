package br.ufrn.imd.obama.response

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class BuscaObjetoAprendizagemResponse(
    val id: Long,

    val nome: String,

    val pathArquivo: String?,

    val acesso: List<AcessoResponse>
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class AcessoResponse(
    val link: String,

    val tipo: String,

    val plataforma: String
)
