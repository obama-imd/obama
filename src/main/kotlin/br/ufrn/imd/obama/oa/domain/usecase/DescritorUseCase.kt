package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Descritor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface DescritorUseCase {
    fun listarDescritor(pageable: Pageable): Page<Descritor>
}
