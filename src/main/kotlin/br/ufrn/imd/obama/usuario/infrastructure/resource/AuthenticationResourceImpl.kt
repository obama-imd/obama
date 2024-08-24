package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.usecase.AuthUseCase
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.LoginResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.RefreshResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    "/v1/auth"
)
@Tag(
    name = "AuthenticationResource",
    description = "Recurso que lida com a autenticação do usuário"
)
class AuthenticationResourceImpl(
    private val usuarioUseCase: UsuarioUseCase,
    private val authUseCase: AuthUseCase
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid request: LoginRequest
    ): ResponseEntity<LoginResponse> {

        //TODO: Remover esse método no futuro, porque fluxo está assumindo mais de uma resposabilidade.
        usuarioUseCase.alterarCriptografiaSenha(email = request.login, senha = request.senha)

        return ResponseEntity.ok().body(
            LoginResponse(
                accessToken = authUseCase.gerarToken(
                    login = request.login,
                    senha = request.senha
                ),
                refreshToken = authUseCase.gerarRefreshToken(
                    login = request.login,
                    senha = request.senha
                )
            )
        )
    }

    @PostMapping("/refresh-token")
    fun refreshToken(
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<RefreshResponse> {
        return ResponseEntity.ok().body(
            RefreshResponse(
                accessToken = authUseCase.atualizarAccessToken(accessToken = token),
                refreshToken = token
            )
        )
    }
}
