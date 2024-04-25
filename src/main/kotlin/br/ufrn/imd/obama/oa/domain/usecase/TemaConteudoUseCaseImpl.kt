package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

class TemaConteudoUseCaseImpl(
    private val temaConteudoDatabaseGateway: TemaConteudoDatabaseGateway
): TemaConteudoUseCase {

    override fun listarTemaConteudos(idCurriculo: Long): Set<TemaConteudo> {
        return temaConteudoDatabaseGateway.listarTemaConteudo(idCurriculo)
    }
}
