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
    private var qtdDownload: Int = 0,
    private var escola: String?,
    private var duracaoEmMinutos: Int? = null,
    private var titulo: String? = null,
    private var resumo: String? = null,
    private var objetivoGeral: String? = null,
    private var objetivosEspecificos: String? = null,
    private var metodologia: String? = null,
    private var avaliacao: String? = null,
    private var referencias: String? = null,
    private var token: String? = null,
    private var status: StatusPlanoAula,
    private var autor: Usuario? = null,
    private var nivelEnsino: NivelEnsino? = null,
    private var disciplinasEnvolvidas: List<Disciplina>? = null,
    private var anoEnsino: AnoEnsino? = null,
    private var objetosAprendizagem: Set<ObjetoAprendizagem>? = null,
    private var coautores: Set<Usuario>? = null,
)
