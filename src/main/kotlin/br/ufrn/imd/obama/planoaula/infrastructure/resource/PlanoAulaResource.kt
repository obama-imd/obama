package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity


interface PlanoAulaResource {

    @Operation(summary = "Endpoint para cadastro de planos de aula")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Plano de Aula cadastrado com sucesso",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PlanoAulaResponse::class)
                )
            ]
        )
    ])
    fun criarPlanoAula(
        header: String,
        planoAula: PlanoAulaRequest
    ): ResponseEntity<PlanoAulaResponse>

}
