package br.ufrn.imd.obama.planoaula.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel

fun PlanoAulaEntity.toModel(): PlanoAula {
    return PlanoAula(
        id = this.id,
        dataCadastro = this.dataCadastro,
        qtdDownload = this.qtdDownload,
        escola = this.escola,
        duracaoEmMinutos = this.duracaoEmMinutos,
        titulo = this.titulo,
        resumo = this.resumo,
        objetivoGeral = this.objetivoGeral,
        objetivosEspecificos = this.objetivosEspecificos,
        metodologia = this.metodologia,
        referencias = this.referencias,
        token = this.token,
        status = this.status,
        autor = this.autor?.toModel(),
        nivelEnsino = this.nivelEnsino?.toModel(),
        disciplinasEnvolvidas = this.disciplinasEnvolvidas?.map { it.toModel() },
        anoEnsino = this.anoEnsino?.toModel(),
        objetosAprendizagem = this.objetosAprendizagem?.map { it.toModel() }?.toSet(),
        coautores = this.coautores?.map { it.toModel() }?.toSet()
    )
}
