package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["mail.active"], havingValue = "false")
class MockEmailGatewayAdapter(
    @Autowired
    private val emailSender: JavaMailSender
): EmailGateway {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun sendSimpleMessage(to: String, subject: String, text: String) {
        logger.info("method={}; to={}; subject={}; text={};", "sendSimpleMessage mockado", to, subject, text)
    }
}