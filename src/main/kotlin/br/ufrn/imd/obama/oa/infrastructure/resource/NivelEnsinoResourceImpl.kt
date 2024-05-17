package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarNivelEnsinoResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.stream.Collectors
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/nivelensino"
)
@Validated
@Tag(
    name = "NivelEnsinoResource",
    description = "Recurso que lida com niveis de ensino"
)
class NivelEnsinoResourceImpl(
    private val nivelEnsinoUseCase: NivelEnsinoUseCase
): NivelEnsinoResource {

    @Cacheable(cacheNames = ["niveisensino"])
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun listarNivelEnsino(): ResponseEntity<Set<ListarNivelEnsinoResponse>> {
        val stream = nivelEnsinoUseCase.listarNiveisEnsino()
            .stream().map { it.toResponse() }

        return ResponseEntity.ok().body(
            stream.collect(Collectors.toSet())
        )
    }
}
