package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.Optional

class UsuarioUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway
): UsuarioUseCase {
    override fun salvarUsuario(usuario: Usuario): Usuario {

        try {
            usuarioGateway.buscarPorEmail(usuario.email)
            throw UsuarioExistenteException("Usuario já existente no banco de dados!")
        } catch (e: UsuarioNaoEncontradoException) {

            return usuarioGateway.salvarUsuario(usuario)
        }
    }

    override fun buscarPorToken(token: String): Optional<Usuario> {
        return Optional.ofNullable(usuarioGateway.buscarPorToken(token))
    }

    override fun ativarUsuario(usuario: Usuario): Unit {
        usuario.ativo = true
        usuarioGateway.salvarUsuario(usuario)
    }
}
