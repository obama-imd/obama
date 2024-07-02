package br.ufrn.imd.obama.planoaula.domain.model

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.time.LocalDateTime

data class PlanoAula(
    val id: Long,
    val dataCadastro: LocalDateTime,
    var escola: String?,
    var qtdDownload: Int = 0,
    var duracaoEmMinutos: Int? = null,
    var titulo: String? = null,
    var resumo: String? = null,
    var objetivoGeral: String? = null,
    var objetivosEspecificos: String? = null,
    var metodologia: String? = null,
    var referencias: String? = null,
    var token: String? = null,
    var status: StatusPlanoAula,
    var autor: Usuario? = null,
    var nivelEnsino: NivelEnsino? = null,
    var disciplinasEnvolvidas: List<Disciplina>? = null,
    var anoEnsino: AnoEnsino? = null,
    var objetosAprendizagem: Set<ObjetoAprendizagem>? = null,
    var coautores: Set<Usuario>? = null,
)
