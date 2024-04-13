package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.HabilidadeResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType

interface HabilidadeResource {

    @Operation(summary = "Endpoint para listar de forma páginada habilidades buscadas pelo Id da habilidade e do tema curricular")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Habilidades encontradas",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Page::class)
                )
            ]
        )
    ])
    fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
        anoEnsinoId: Long,
        temaConteudoId: Long,
        pageable: Pageable
    ): Page<HabilidadeResponse>
}