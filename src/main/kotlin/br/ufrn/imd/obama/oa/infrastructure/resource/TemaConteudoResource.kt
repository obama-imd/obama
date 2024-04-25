package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarTemaConteudoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface TemaConteudoResource {

    @Operation(summary = "Endpoint para listar temas conteudos existentes")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Tema conteudo encontrados",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Set::class)
                )
            ]
        )
    ])
    fun listarTemasConteudos(
        idCurriculo: Long
    ): ResponseEntity<Set<ListarTemaConteudoResponse>>
}
