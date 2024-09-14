package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.TokenInvalidoException
import br.ufrn.imd.obama.usuario.domain.gateway.TokenGateway
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AuthUseCaseImpl(
    private val tokenGateway: TokenGateway,
    private val authenticationManager: AuthenticationManager,
    private val usuarioUseCase: UsuarioUseCase
): AuthUseCase {

    override fun gerarToken(login: String, senha: String): String {

        val usernamePassword = UsernamePasswordAuthenticationToken(login, senha)

        val auth = authenticationManager.authenticate(usernamePassword)

        return tokenGateway.gerarToken(
            (auth.principal as UsuarioEntity).toModel(),
            false
        )
    }

    override fun gerarRefreshToken(login: String, senha: String): String {
        val usernamePassword = UsernamePasswordAuthenticationToken(login, senha)

        val auth = authenticationManager.authenticate(usernamePassword)

        return tokenGateway.gerarToken(
            (auth.principal as UsuarioEntity).toModel(),
            true
        )
    }

    override fun atualizarAccessToken(accessToken: String): String {
        val accessTokenSanitizado = accessToken.replace("Bearer ", "")

        if (tokenGateway.tokenValido(token = accessTokenSanitizado)) {
            val username = tokenGateway.extrairUsernameDoToken(token = accessTokenSanitizado)

            val novoAccessToken = tokenGateway.gerarToken(
                usuarioUseCase.buscarPorEmail(email = username),
                false
            )

            return novoAccessToken
        }
        throw TokenInvalidoException()
    }

}
