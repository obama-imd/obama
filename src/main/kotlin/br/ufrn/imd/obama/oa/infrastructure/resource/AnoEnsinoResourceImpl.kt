package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.AnoEnsinoUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarAnoEnsinoResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/anoEnsino")
@Validated
@Tag(
    name = "AnoEnsino",
    description = "Recurso que lida com anos de ensino"
)
class AnoEnsinoResourceImpl(
    private val anoEnsinoUseCase: AnoEnsinoUseCase
) : AnoEnsinoResource {

    @GetMapping
    override fun listarAnosEnsino(pageable: Pageable): Page<ListarAnoEnsinoResponse> {
        return anoEnsinoUseCase.listarAnosEnsino(pageable).map { it.toResponse() }
    }

}
