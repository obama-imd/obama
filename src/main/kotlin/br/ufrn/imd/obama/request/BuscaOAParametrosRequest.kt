package br.ufrn.imd.obama.request

import br.ufrn.imd.obama.enums.TipoVisualizacao
import br.ufrn.imd.obama.validation.ValorTipoVisualizacaoEnum
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotBlank

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class BuscaOAParametrosRequest (
    @field:NotBlank
    val nome: String,

    val nivelEnsinoId: Long?,

    val temaConteudoId: Long?,

    val descritorId: Long?,

    val habilidadeId: Long?,

    @field:ValorTipoVisualizacaoEnum(enumClass = TipoVisualizacao::class)
    val tipoVisualizacao: String?,

    val curriculo: String?
)
