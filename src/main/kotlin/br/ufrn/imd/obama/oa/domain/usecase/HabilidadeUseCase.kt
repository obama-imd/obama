package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.HabilidadeV2
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HabilidadeUseCase {
    fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(anoEnsinoId: Long,
                                                         temaConteudoId: Long,
                                                         pageable: Pageable): Page<HabilidadeV2>
}