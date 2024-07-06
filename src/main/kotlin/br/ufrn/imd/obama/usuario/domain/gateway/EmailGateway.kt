package br.ufrn.imd.obama.usuario.domain.gateway

interface EmailGateway {
    fun enviarEmail(to: String, subject: String, text: String)
}