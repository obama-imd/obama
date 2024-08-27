package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.TokenGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.configuration.TokenConfiguration
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenGatewayAdapter(
    private val tokenConfiguration: TokenConfiguration
): TokenGateway {
    val algorithm = Algorithm.HMAC256(tokenConfiguration.tokenSecret)

    override fun gerarToken(usuario: Usuario, isRefresh: Boolean): String {
        try {
            return JWT.create().withIssuer(tokenConfiguration.tokenIssuer)
                .withSubject(usuario.email)
                .withExpiresAt(
                    gerarDataExpiracaoToken(
                        isRefresh
                    )
                )
                .sign(algorithm)

        } catch (ex: JWTCreationException) {
            throw JWTCreationException("Erro criando o token", ex)
        }
    }

    private fun gerarDataExpiracaoToken(isRefresh: Boolean): Instant {
        return LocalDateTime.now().plusMinutes(
            if(isRefresh) tokenConfiguration.refreshTokenExpiration
            else tokenConfiguration.accessTokenExpiration
        ).toInstant(ZoneOffset.of("-03:00"))
    }

    override fun validarToken(token: String): String {
        try {
            return JWT.require(algorithm)
                .withIssuer(tokenConfiguration.tokenIssuer)
                .build()
                .verify(token)
                .subject
        } catch (ex: JWTVerificationException) {
            return ""
        }
    }

    override fun extrairUsernameDoToken(token: String): String {
        return JWT.decode(token).subject
    }

    override fun tokenValido(token: String): Boolean {
        return try {
            val verifier = JWT.require(algorithm)
                .withIssuer(tokenConfiguration.tokenIssuer)
                .build()
            verifier.verify(token)
            true
        } catch (e: JWTVerificationException) {
            false
        }
    }
}
