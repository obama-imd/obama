package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class EmailGatewayAdapter(
    private val mailSender: JavaMailSender,
    private val emailRemetente: String
): EmailGateway {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun enviarEmail(emailDestinatario: String, assunto: String, texto: String) {
        val message = SimpleMailMessage()

        message.from = emailRemetente
        message.setTo(emailDestinatario)
        message.subject = assunto
        message.text = texto

        try {
            mailSender.send(message)
        } catch (e: Exception) {
            logger.error(e.message)
        }
    }
}
