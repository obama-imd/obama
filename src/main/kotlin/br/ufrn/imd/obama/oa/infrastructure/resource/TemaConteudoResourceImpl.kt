package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarTemaConteudoResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.stream.Collectors
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/temaconteudo"
)
@Validated
@Tag(
    name = "TemaConteudoResource",
    description = "Recurso que lida com temas contéudos"
)
class TemaConteudoResourceImpl(
    private val temaConteudoUseCase: TemaConteudoUseCase
): TemaConteudoResource {

    @Cacheable(cacheNames = ["temaconteudos"])
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun listarTemasConteudos(
        @RequestParam( value="curriculo",required = true) curriculo: String
    ): ResponseEntity<Set<ListarTemaConteudoResponse>> {
        val stream = temaConteudoUseCase.listarTemaConteudos(curriculo).stream().map { it.toResponse() }

        return ResponseEntity.ok(stream.collect(Collectors.toSet()));
    }
}
