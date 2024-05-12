package br.ufrn.imd.obama.usuario.domain.gateway

import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface UsuarioGateway {
    fun salvarUsuario(usuario: Usuario): Usuario
}