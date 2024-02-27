package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarNivelEnsinoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface NivelEnsinoResource {

    @Operation(summary = "Endpoint para listar niveis de ensino existente")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Niveis de ensino encontrados",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Set::class)
                )
            ]
        )
    ])
    fun listarNivelEnsino(): ResponseEntity<Set<ListarNivelEnsinoResponse>>
}
