package br.ufrn.imd.obama.usuario.infrastructure.adapter

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.mail.javamail.JavaMailSender
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage

@ExtendWith(MockitoExtension::class)
class EmailGatewayAdapterTest {

    @Mock
    private lateinit var emailSender: JavaMailSender // Substitua EmailService pela dependência real

    @InjectMocks
    private lateinit var emailGatewayAdapter: EmailGatewayAdapter

    @Test
    fun `Deve enviar email com sucesso`() {

        val email = "test@example.com"
        val subject = "Test Subject"
        val body = "Test Body"

        val message = SimpleMailMessage()
        message.setTo(email)
        message.subject = subject
        message.text = body

        assertDoesNotThrow {
            emailGatewayAdapter.enviarEmail(email, subject, body)
        }

        verify(emailSender, times(1)).send(message)
    }


    @Test
    fun `Não deve enviar email com sucesso e não deve lançar exceção`() {

        val email = "test@example.com"
        val subject = "Test Subject"
        val body = "Test Body"

        val message = SimpleMailMessage()
        message.setTo(email)
        message.subject = subject
        message.text = body

        `when`(emailSender.send(any(SimpleMailMessage::class.java))).thenThrow(MailSendException::class.java)

        assertDoesNotThrow {
            emailGatewayAdapter.enviarEmail(email, subject, body)
        }

        verify(emailSender, times(1)).send(message)
    }

}