package br.ufrn.imd.obama.planoaula.domain.gateway

interface PlanoAulaGateway {

    fun salvarPlanoAula(
        escola: String?,
        idNivelEnsino: Long?,
        disciplinasEnvolvidas: List<Long>?,
        idAnoEnsino: Long?,
        duracaoEmMinutos: Int?,
        titulo: String?,
        metodologia: String?,
        objetivosEspecificos: String?,
        objetivoGeral: String?,
        avaliacao: String?,
        referencias: String?
    ): Long
}