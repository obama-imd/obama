package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
class PlanoAulaBuscarPorIdResponse(

    val id: Long,
    val titulo: String?,
    val escola: String?,
    val resumo: String?,
    val objetivoGeral: String?,
    val objetivosEspecificos: String?,
    val metodologia: String?,
    val referencias: String?,
    val nivelEnsinoId: Long?,
    val idDisciplinas: List<Long>,
    val anoEnsinoId: Long?,
    val emailCoautores: List<String>,
    val idObjetosAprendizagem: List<Long>
)
