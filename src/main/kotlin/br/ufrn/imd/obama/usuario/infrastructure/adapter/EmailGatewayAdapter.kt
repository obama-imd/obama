package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["mail.active"], havingValue = "true")
@Primary
class EmailGatewayAdapter(
    @Autowired
    private val emailSender: JavaMailSender
): EmailGateway {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun enviarEmail(to: String, subject: String, text: String) {
        logger.info("method={} started", "sendSimpleMessage")

        val message = SimpleMailMessage()
        message.setTo(to)
        message.subject = subject
        message.text = text

        try {
            emailSender.send(message)
            logger.info("method={} finished", "sendSimpleMessage")
        }catch (e: MailException) {
            logger.error("method={}; exceptionMessage={};", "sendSimpleMessage", e.message)
        }
    }
}