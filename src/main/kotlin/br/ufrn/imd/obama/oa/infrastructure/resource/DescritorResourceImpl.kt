package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDescritorResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/descritor"
)
@Validated
@Tag(
    name = "DescritorResource",
    description = "Recurso que lida com descritores"
)
class DescritorResourceImpl(
    private val descritorUseCase: DescritorUseCase
): DescritorResource {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun listarDescritores(
        pageable: Pageable
    ): Page<ListarDescritorResponse> {
        return descritorUseCase.listarDescritor(pageable).map {
            it.toResponse()
        }
    }
}
