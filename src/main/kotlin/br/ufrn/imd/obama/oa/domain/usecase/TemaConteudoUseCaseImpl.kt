package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.domain.exception.CurriculoNaoEncontradoException

class TemaConteudoUseCaseImpl(
    private val temaConteudoDatabaseGateway: TemaConteudoDatabaseGateway
): TemaConteudoUseCase {

    override fun listarTemaConteudos(curriculo: String): Set<TemaConteudo> {
        val curriculoEnum = Curriculo.values().toList().find { it.name == curriculo }
            ?: throw CurriculoNaoEncontradoException("Curriculo inválido")

        return temaConteudoDatabaseGateway.listarTemaConteudo(curriculoEnum)
    }
}
