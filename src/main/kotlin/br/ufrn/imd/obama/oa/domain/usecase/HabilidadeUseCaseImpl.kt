package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.model.Habilidade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class HabilidadeUseCaseImpl(
    private val habilidadeGateway: HabilidadeGateway
): HabilidadeUseCase {
    override fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
        anoEnsinoId: Long?,
        temaConteudoId: Long?,
        pageable: Pageable
    ): Page<Habilidade> {
        return habilidadeGateway.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId, temaConteudoId, pageable)
    }
}