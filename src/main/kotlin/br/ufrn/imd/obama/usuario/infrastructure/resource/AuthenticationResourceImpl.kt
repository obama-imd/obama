package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenService
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/auth"
)
@Tag(
    name = "AuthenticationResource",
    description = "Recurso que lida com a autenticação do usuário"
)
class AuthenticationResourceImpl(
    private val authenticationManager: AuthenticationManager,

    private val tokenService: TokenService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid request: LoginRequest
    ): ResponseEntity<LoginResponse> {
        val usernamePassword = UsernamePasswordAuthenticationToken(request.login, request.senha)

        val auth = authenticationManager.authenticate(usernamePassword)

        val token = tokenService.gerarToken( auth.principal as UsuarioEntity)

        return ResponseEntity.ok().body(
            LoginResponse(token)
        )
    }
}
