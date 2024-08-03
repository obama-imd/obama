package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.TokenGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import io.jsonwebtoken.Jwts
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenGatewayAdapter(
    @Value("\${api.security.token.issuer}")
    private val tokenIssuer: String,

    @Value("\${api.security.token.secret}")
    private val tokenSecret: String,

    @Value("\${token.accessExpiration}")
    private val accessTokenExpiration: Long,

    @Value("\${token.refreshExpiration}")
    private val refreshTokenExpiration: Long
): TokenGateway {

    override fun gerarToken(usuario: Usuario, isRefresh: Boolean): String {
        try {
            val algorithm = Algorithm.HMAC256(tokenSecret)

            return JWT.create().withIssuer(tokenIssuer)
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
            if(isRefresh) refreshTokenExpiration else accessTokenExpiration
        ).toInstant(ZoneOffset.of("-03:00"))
    }

    override fun validarToken(token: String): String {
        try {
            val algorithm = Algorithm.HMAC256(tokenSecret)

            return JWT.require(algorithm).withIssuer(tokenIssuer)
                .build()
                .verify(token)
                .subject

        } catch (ex: JWTVerificationException) {
            return ""
        }
    }

    override fun extrairUsernameDoToken(token: String): String {
        return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).body.subject
    }
}
