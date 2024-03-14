package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

class TemaConteudoUseCaseImpl(
    private val temaConteudoDatabaseGateway: TemaConteudoDatabaseGateway
): br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase {


    override fun listarTemaConteudos(): Set<TemaConteudo> {
        return temaConteudoDatabaseGateway.listarTemaConteudo()
    }
}
