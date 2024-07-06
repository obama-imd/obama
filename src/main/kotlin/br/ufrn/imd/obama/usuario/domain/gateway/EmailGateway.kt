package br.ufrn.imd.obama.usuario.domain.gateway

interface EmailGateway {
    fun sendSimpleMessage(to: String, subject: String, text: String)
}