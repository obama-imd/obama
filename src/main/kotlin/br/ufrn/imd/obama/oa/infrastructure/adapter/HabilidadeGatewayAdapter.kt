package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.model.HabilidadeV2
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModelV2
import br.ufrn.imd.obama.oa.infrastructure.repository.HabilidadeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HabilidadeGatewayAdapter(
    private val habilidadeRepository: HabilidadeRepository
): HabilidadeGateway {
    override  fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId: Long,
                                                                   temaConteudoId: Long,
                                                                   pageable: Pageable
    ): Page<HabilidadeV2> {
        val habilidadesPage = habilidadeRepository.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId, temaConteudoId, pageable)
        return habilidadesPage.map {
            habilidade -> habilidade.toModelV2()
        }
    }
}