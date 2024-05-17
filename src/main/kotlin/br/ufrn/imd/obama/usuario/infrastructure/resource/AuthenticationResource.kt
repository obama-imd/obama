package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface AuthenticationResource {

    @Operation(summary = "Endpoint para usuário fazer login")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Login realizado com sucesso",
            content = [
                Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = LoginResponse::class)
                )
            ]
        )
    ])
    fun login(
        request: LoginRequest
    ): ResponseEntity<LoginResponse>
}
