package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.ListarNivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarNivelEnsinoResponse
import java.util.stream.Collectors
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
class NivelEnsinoResourceImpl(
    private val listarNivelEnsino: ListarNivelEnsino
): NivelEnsinoResource {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun listarNivelEnsino(): ResponseEntity<Set<ListarNivelEnsinoResponse>> {
        val stream = listarNivelEnsino.listarNiveisEnsino()
            .stream().map { it.toResponse() }

        return ResponseEntity.ok().body(
            stream.collect(Collectors.toSet())
        )
    }
}
