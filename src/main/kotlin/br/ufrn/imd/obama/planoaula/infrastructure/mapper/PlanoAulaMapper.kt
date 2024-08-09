package br.ufrn.imd.obama.planoaula.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity

fun PlanoAula.toResponse(): PlanoAulaResponse {
    return PlanoAulaResponse(
        id = this.id,
        titulo = this.getTitulo(),
        status = this.getStatus()
    )
}

fun PlanoAula.toEntity(): PlanoAulaEntity {
    return PlanoAulaEntity(
        id = this.id,
        dataCadastro = this.dataCadastro,
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
        autor = this.getAutor()?.toEntity(),
        nivelEnsino = this.getNivelEnsino()?.toEntity(),
        disciplinasEnvolvidas = this.getDisciplinasEnvolvidas()?.map { it.toEntity() }?.toList(),
        anoEnsino = this.getAnoEnsino()?.toEntity(),
        objetosAprendizagem = this.getObjetosAprendizagem()?.map { it.toEntity() }?.toSet(),
        coautores = this.getCoautores()?.map { it.toEntity() }?.toSet(),
    )
}
