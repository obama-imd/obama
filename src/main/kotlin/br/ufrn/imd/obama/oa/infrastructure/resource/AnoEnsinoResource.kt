package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarAnoEnsinoResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType


interface AnoEnsinoResource {

    @Operation(summary = "Endpoint para listar anos de ensino cadastrados")
    @ApiResponses(value =[
        ApiResponse(
            responseCode = "200",
            description = "Anos de ensino encontrados",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Set::class)
                )
            ]
        )
    ])
    fun listarAnosEnsino(
        pageable: Pageable
    ) : Page<ListarAnoEnsinoResponse>
}
