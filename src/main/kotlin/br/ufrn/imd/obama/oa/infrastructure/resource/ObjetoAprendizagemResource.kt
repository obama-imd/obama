package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ObjetoAprendizagemRequest
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ObjetoAprendizagemResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?
    ): Page<BuscarOaResponse>

    @Operation(summary = "Endpoint para cadastrar um objeto de aprendizagem")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Objeto de aprendizagem cadastrado com sucesso",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ObjetoAprendizagemResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Requisição inválida - dados malformados ou incompletos",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Não encontrado: algum recurso relacionado (descritor, tema de conteúdo, etc.) não existe",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Erro interno no servidor",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ErrorResponse::class)
                    )
                ]
            )
        ]
    )
    fun cadastrarObjetoAprendizagem(
        objetoAprendizagem: ObjetoAprendizagemRequest
    ): ResponseEntity<ObjetoAprendizagemResponse>

}
