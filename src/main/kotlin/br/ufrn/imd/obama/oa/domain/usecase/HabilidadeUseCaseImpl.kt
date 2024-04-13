package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.model.HabilidadeV2
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class HabilidadeUseCaseImpl(
    private val habilidadeGateway: HabilidadeGateway
): HabilidadeUseCase {
    override fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
        anoEnsinoId: Long,
        temaConteudoId: Long,
        pageable: Pageable
    ): Page<HabilidadeV2> {
        return habilidadeGateway.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId, temaConteudoId, pageable)
    }
}