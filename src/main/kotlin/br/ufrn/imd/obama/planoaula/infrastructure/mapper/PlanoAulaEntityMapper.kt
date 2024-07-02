package br.ufrn.imd.obama.planoaula.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel

fun PlanoAulaEntity.toModel(): PlanoAula {
    val planoAula = PlanoAula(
        id = this.getId(),
        dataCadastro = this.getDataCadastro(),
        qtdDownload = this.getQtdDownload(),
        escola = this.getEscola(),
        duracaoEmMinutos = this.getDuracaoEmMinutos(),
        titulo = this.getTitulo(),
        resumo = this.getResumo(),
        objetivoGeral = this.getObjetivoGeral(),
        objetivosEspecificos = this.getObjetivosEspecificos(),
        metodologia = this.getMetodologia(),
        referencias = this.getReferencias(),
        token = this.getToken(),
        status = this.getStatus(),
        autor = this.getAutor()?.toModel(),
        nivelEnsino = this.getNivelEnsino()?.toModel(),
        disciplinasEnvolvidas = this.getDisciplinasEnvolvidas()?.map { it.toModel() }?.toList(),
        anoEnsino = this.getAnoEnsino()?.toModel(),
        objetosAprendizagem = this.getObjetosAprendizagem()?.map { it.toModel() }?.toSet(),
        coautores = this.getCoautores()?.map { it.toModel() }?.toSet(),
    )

    return planoAula
}
