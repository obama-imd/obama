package br.ufrn.imd.obama.usuario.domain.gateway

interface EmailGateway {

    fun enviarEmail(emailDestinatario: String, assunto: String, texto: String)
}
