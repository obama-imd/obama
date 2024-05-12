package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

interface UsuarioResource {
    @Operation(summary = "Endpoint para salvar um usuário")
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
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
                    schema = Schema(implementation = ResponseEntity::class) //criar uma classe de erro personalizada para o usuário
                )
            ]
        )
    ])
    fun salvarUsuario(usuario: Usuario): ResponseEntity<UsuarioResponse>
}
