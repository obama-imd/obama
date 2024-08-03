package br.ufrn.imd.obama.usuario.domain.gateway

import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface TokenGateway {
    fun gerarToken(usuario: Usuario, isRefresh: Boolean): String

    fun validarToken(token: String): String

    fun extrairUsernameDoToken(token: String): String
}
