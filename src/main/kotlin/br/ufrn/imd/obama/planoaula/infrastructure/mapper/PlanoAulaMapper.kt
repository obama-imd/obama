package br.ufrn.imd.obama.planoaula.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel

fun PlanoAula.toResponse(): PlanoAulaResponse {
    val response = PlanoAulaResponse(
        id = this.id,
        titulo = this.getTitulo(),
        status = this.getStatus()
    )
    return response
}

fun PlanoAula.toEntity(): PlanoAulaEntity {
    val entity = PlanoAulaEntity(
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
        avaliacao = this.getAvaliacao(),
        disciplinasEnvolvidas = this.getDisciplinasEnvolvidas()?.map { it.toEntity() }?.toList(),
        anoEnsino = this.getAnoEnsino()?.toEntity(),
        objetosAprendizagem = this.getObjetosAprendizagem()?.map { it.toEntity() }?.toSet(),
        coautores = this.getCoautores()?.map { it.toEntity() }?.toSet(),
    )
    return entity
}

fun PlanoAula.toPlanoAulaBuscarPorIdResponse(): PlanoAulaBuscarPorIdResponse {
    val response = PlanoAulaBuscarPorIdResponse(
        id = this.id,
        escola = this.getEscola(),
        titulo = this.getTitulo(),
        resumo = this.getResumo(),
        objetivoGeral = this.getObjetivoGeral(),
        objetivosEspecificos = this.getObjetivosEspecificos(),
        metodologia = this.getMetodologia(),
        referencias = this.getReferencias(),
        idObjetosAprendizagem = this.getObjetosAprendizagem()?.map { it.id }?.toList() ?: emptyList(),
        nivelEnsinoId = this.getNivelEnsino()?.id,
        idDisciplinas = this.getDisciplinasEnvolvidas()?.map { it.id }?.toList() ?: emptyList(),
        anoEnsinoId = this.getAnoEnsino()?.id,
        emailCoautores = this.getCoautores()?.map { it.email }?.toList() ?: emptyList()
        )

    return response
}

