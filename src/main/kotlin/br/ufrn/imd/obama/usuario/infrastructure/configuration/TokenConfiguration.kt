package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.TokenGateway
import br.ufrn.imd.obama.usuario.infrastructure.adapter.TokenGatewayAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class TokenConfiguration(
    @Value("\${api.security.token.issuer}") val tokenIssuer: String,

    @Value("\${api.security.token.secret}") val tokenSecret: String,

    @Value("\${api.security.token.accessExpiration}")
    val accessTokenExpiration: Long,

    @Value("\${api.security.token.refreshExpiration}")
    val refreshTokenExpiration: Long
) {

    @Bean
    @Primary
    fun tokenGateway(tokenConfiguration: TokenConfiguration): TokenGateway {
        return TokenGatewayAdapter(
            tokenConfiguration
        )
    }
}
