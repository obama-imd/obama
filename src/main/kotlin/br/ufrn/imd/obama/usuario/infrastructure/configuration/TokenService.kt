package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import java.lang.RuntimeException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    private val tokenSecret: String
) {

    fun gerarToken(usuario: UsuarioEntity): String {
        try {
            val algorithm = Algorithm.HMAC256(tokenSecret)

            return JWT.create().withIssuer("obama-api")
                .withSubject(usuario.email)
                .withExpiresAt(gerarDataExpiracaoToken())
                .sign(algorithm)

        } catch (ex: JWTCreationException) {
            throw RuntimeException("Erro criando o token", ex)
        }
    }

    private fun gerarDataExpiracaoToken(): Instant {
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-03:00"))
    }

    fun validarToken(token: String): String {
        try {
            val algorithm = Algorithm.HMAC256(tokenSecret)

            return JWT.require(algorithm).withIssuer("obama-api")
                .build()
                .verify(token)
                .subject

        } catch (ex: JWTVerificationException) {
            return ""
        }
    }
}
