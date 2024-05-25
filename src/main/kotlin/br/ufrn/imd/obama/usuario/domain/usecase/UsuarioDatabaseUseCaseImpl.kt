package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario

class UsuarioDatabaseUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway
): UsuarioDatabaseUseCase {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        return usuarioGateway.salvarUsuario(usuario)
    }
}