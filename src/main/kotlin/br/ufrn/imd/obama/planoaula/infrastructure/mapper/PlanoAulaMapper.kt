package br.ufrn.imd.obama.planoaula.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity

fun PlanoAula.toEntity(): PlanoAulaEntity {
    return PlanoAulaEntity(
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
        avaliacao = this.avaliacao,
        referencias = this.referencias,
        token = this.token,
        status = this.status,
        autor = this.autor?.toEntity(),
        nivelEnsino = this.nivelEnsino?.toEntity(),
        disciplinasEnvolvidas = this.disciplinasEnvolvidas?.map { it.toEntity() },
        anoEnsino = this.anoEnsino?.toEntity(),
        objetosAprendizagem = this.objetosAprendizagem?.map { it.toEntity() }?.toSet(),
        coautores = this.coautores?.map { it.toEntity() }?.toSet()
    )
}

fun PlanoAula.toResponse(): PlanoAulaResponse {
    return PlanoAulaResponse(id)
}
