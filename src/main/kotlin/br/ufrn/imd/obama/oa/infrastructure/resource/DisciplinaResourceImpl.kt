package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.DisciplinaUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDisciplinaResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/disciplina")
@Validated
@Tag(
    name = "DisciplinaResource",
    description = "Recurso que lida com disciplinas"
)
class DisciplinaResourceImpl(
    private val disciplinaUseCase: DisciplinaUseCase
) : DisciplinaResource {

    @Cacheable(cacheNames = ["disciplinas"])
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun listarDisciplinas(
        pageable: Pageable
    ): Page<ListarDisciplinaResponse> {
        return disciplinaUseCase.listarDisciplinas(pageable).map { it.toResponse() }
    }
}
