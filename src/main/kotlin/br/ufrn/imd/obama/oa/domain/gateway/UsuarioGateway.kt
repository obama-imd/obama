package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Usuario

interface UsuarioGateway {
    fun salvarUsuario(name: String, sobrenome: String, email: String, senha: String): Usuario
}