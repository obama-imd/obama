package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.AnoEnsino
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AnoEnsinoUseCase {

    fun listarAnosEnsino(pageable: Pageable) : Page<AnoEnsino>
}
