package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.infrastructure.handler.UsuarioExceptionHandler
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface UsuarioDatabaseResource {
    @Operation(summary = "Endpoint para salvar um usuário")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "201",
            description = "Usuário salvo com sucesso",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = UsuarioResponse::class)
                )
            ]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Erro ao salvar usuário",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = UsuarioExceptionHandler::class)
                )
            ]
        )
    ])
    fun salvarUsuario(@Valid request: CadastrarUsuarioRequest): ResponseEntity<UsuarioResponse>
}
