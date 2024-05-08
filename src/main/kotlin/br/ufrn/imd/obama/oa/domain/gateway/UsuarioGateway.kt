package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Usuario

interface UsuarioGateway {
    fun salvarUsuario(usuario: Usuario): Usuario
}