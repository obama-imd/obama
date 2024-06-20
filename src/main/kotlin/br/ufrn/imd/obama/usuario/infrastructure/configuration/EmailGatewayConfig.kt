package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import br.ufrn.imd.obama.usuario.infrastructure.adapter.EmailGatewayAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class EmailGatewayConfig(
    @Value("\${app.email-sender}")
    private val remetente: String
) {

    @Bean
    fun config(javaMailSender: JavaMailSender): EmailGateway {
        return EmailGatewayAdapter(javaMailSender, remetente)
    }
}
