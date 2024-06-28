package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula

interface PlanoAulaGateway {

    fun salvarPlanoAula(
        token: String,
        escola: String?,
        idNivelEnsino: Long?,
        disciplinasEnvolvidas: List<Long>?,
        idAnoEnsino: Long?,
        duracaoEmMinutos: Int?,
        titulo: String?,
        resumo: String?,
        metodologia: String?,
        objetivosEspecificos: String?,
        objetivoGeral: String?,
        avaliacao: String?,
        referencias: String?
    ): PlanoAula
}
