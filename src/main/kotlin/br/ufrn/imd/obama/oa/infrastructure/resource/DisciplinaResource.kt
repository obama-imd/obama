package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ListarDisciplinaResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType

interface DisciplinaResource {

    @Operation(summary = "Endpoint para listagem de disciplinas")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Listagem de disciplinas",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Page::class)
                )
            ]
        )
    ])
    fun listarDisciplinas(
        pageable: Pageable
    ) : Page<ListarDisciplinaResponse>
}
