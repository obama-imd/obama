package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDisciplinaResponse
import org.springframework.http.ResponseEntity

interface DisciplinaUseCase {

    fun listarDisciplinas(): ResponseEntity<ListarDisciplinaResponse>
}
