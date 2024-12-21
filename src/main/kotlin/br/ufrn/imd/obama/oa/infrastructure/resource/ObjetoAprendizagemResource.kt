package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.ErrorResponse

interface ObjetoAprendizagemResource {

    @Operation(summary = "Endpoint para consulta de objeto de aprendizagem por id")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Objeto de aprendizagem encontrado",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Page::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "404",
            description = "Objeto de aprendizagem não encontrado",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )
            ]
        )
    ])
    fun buscarPorId(
        id: Long
    ): ResponseEntity<BuscarOaIdResponse>

    @Operation(summary = "Endpoint para consulta de objetos de aprendizagem por parâmetros")
    @ApiResponses(value = [
        ApiResponse(
                responseCode = "200",
                description = "Objetos de aprendizagem encontrados",
                content = [
                    Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = Schema(implementation = Page::class)
                    )
                ]
        )
    ])
    fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        tipoAcesso: TipoAcesso?
    ): Page<BuscarOaResponse>

    @Operation(summary = "Endpoint para usuário favoritar um objeto de aprendizagem por id")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Objeto de aprendizagem salvo como favorito",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Page::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "404",
            description = "Objeto de aprendizagem não encontrado",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )
            ]
        )
    ])
    fun favoritarPorId(
        usuarioDetails: UserDetails,
        id: Long
    ): ResponseEntity<Void>

}
