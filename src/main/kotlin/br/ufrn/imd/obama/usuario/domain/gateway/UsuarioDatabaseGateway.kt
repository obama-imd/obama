package br.ufrn.imd.obama.usuario.domain.gateway

import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface UsuarioDatabaseGateway {
   fun buscarPorEmail(email: String): Usuario

   fun salvarUsuario(usuario: Usuario): Usuario
}
