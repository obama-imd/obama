package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType


interface PlanoAulaResource {
    @Operation(summary = "Endpoint para buscar planos de aula por título")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Planos de aula encontrados",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Page::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Usuário não autenticado",
        )
    ])
    fun buscarPlanosAulaPorTitulo(titulo: String?, pageable: Pageable): Page<PlanoAulaResponse>
}
