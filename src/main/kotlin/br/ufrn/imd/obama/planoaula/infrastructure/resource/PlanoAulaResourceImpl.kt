package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/planoaula")
@Validated
@Tag(
    name = "PlanoAulaResource",
    description = "Recurso que lida com a manipulação do plano de aula no banco de dados"
)
class PlanoAulaResourceImpl(
    private val planoAulaUseCase: PlanoAulaUseCase
): PlanoAulaResource {
    @Cacheable(cacheNames = ["planosaula"])
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPlanosAulaPorTitulo(@RequestParam(value = "titulo", required = false) titulo: String?,
                                           pageable: Pageable): Page<PlanoAulaResponse> {
        return planoAulaUseCase.buscarPlanoAulaPorTitulo(titulo, pageable).map { it.toResponse() }
    }
}
