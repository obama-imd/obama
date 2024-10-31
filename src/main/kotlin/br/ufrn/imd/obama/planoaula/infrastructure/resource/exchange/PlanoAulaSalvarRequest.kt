package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class PlanoAulaSalvarRequest(
    val escola: String?,
    val idNivelEnsino: Long?,
    val disciplinasEnvolvidas: List<Long>?,
    val idAnoEnsino: Long?,
    val duracaoEmMinutos: Int?,
    val titulo: String?,
    val metodologia: String?,
    val objetivosEspecificos: String?,
    val objetivoGeral: String?,
    val avaliacao: String?,
    val referencias: String?
)
