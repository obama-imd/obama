package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
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
        ),
        ApiResponse(
            responseCode = "400",
            description = "Inconsistência dos dados informados, isto é: " +
                    "o ID do nivelEnsino é inválido e/ou " +
                    "a duração em minutos é menor do que zero e/ou "  +
                    "o ID de alguma disciplina é inválido e/ou " +
                    "o ID do anoEnsino é inválido",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Exception::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Usuário não autenticado",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = Exception::class)
                )
            ]
        )
    ])
    fun criarPlanoAula(
        userDetails: UserDetails,
        planoAula: PlanoAulaRequest
    ): ResponseEntity<PlanoAulaResponse>

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
    fun buscarPlanosAulaPorTitulo(
        usuario: UserDetails,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAulaResponse>

    @Operation(summary = "Endpoint para buscar planos de aula por id")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Plano de aula encontrado",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = PlanoAulaResponse::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "403",
            description = "Usuário não autenticado; o plano de aula não pertece ao usuário ou usuário não é coator",
        ),
        ApiResponse(
            responseCode = "404",
            description = "Plano de aula não encontrado",
        )
    ])
    fun buscarPlanoAulaPorId(id: Long): PlanoAulaBuscarPorIdResponse

}
