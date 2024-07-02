package br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange

data class PlanoAulaRequest(
    var escola: String?,
    var idNivelEnsino: Long?,
    var disciplinasEnvolvidas: List<Long>?,
    var idAnoEnsino: Long?,
    var duracaoEmMinutos: Int?,
    var titulo: String?,
    var resumo: String?,
    var metodologia: String?,
    var objetivosEspecificos: String?,
    var objetivoGeral: String?,
    var referencias: String?
)
