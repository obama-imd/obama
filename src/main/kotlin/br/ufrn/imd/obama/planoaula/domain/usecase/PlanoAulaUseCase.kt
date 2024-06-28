package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula

interface PlanoAulaUseCase {

    fun criarPlanoAula(
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
