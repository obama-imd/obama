package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Habilidade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HabilidadeGateway {
    fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId: Long?,
                                                         temaConteudoId: Long?,
                                                         pageable: Pageable): Page<Habilidade>

}