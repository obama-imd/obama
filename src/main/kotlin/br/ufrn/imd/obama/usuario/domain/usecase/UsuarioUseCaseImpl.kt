package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioGateway
import br.ufrn.imd.obama.oa.domain.model.Usuario

class UsuarioUseCaseImpl(
    private val usuarioGateway: UsuarioGateway
): UsuarioUseCase {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        return usuarioGateway.salvarUsuario(usuario)
    }
}