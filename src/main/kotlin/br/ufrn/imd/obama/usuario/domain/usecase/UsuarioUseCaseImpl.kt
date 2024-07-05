package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.configuration.OldCustomEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class UsuarioUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway,
    private val passwordEncoder: PasswordEncoder,
    private val oldCustomEncoder: OldCustomEncoder
): UsuarioUseCase {
    override fun salvarUsuario(usuario: Usuario): Usuario {

        usuario.senha = passwordEncoder.encode(usuario.senha)
        usuario.usaCriptografiaAntiga = false

        try {
            usuarioGateway.buscarPorEmail(usuario.email)
            throw UsuarioExistenteException("Usuario já existente no banco de dados!")
        } catch (e: UsuarioNaoEncontradoException) {

            return usuarioGateway.salvarUsuario(usuario)
        }
    }

    override fun buscarPorToken(token: String): Usuario {
        return usuarioGateway.buscarPorToken(token)
    }

    override fun ativarUsuario(usuario: Usuario): Unit {
        usuario.ativo = true
        usuarioGateway.salvarUsuario(usuario)
    }

    override fun alterarCriptografiaSenha(email: String, senha: String) {
        val usuario = usuarioGateway.buscarPorEmail(email)

        if(!usuario.usaCriptografiaAntiga) {
            return
        }

        if(!oldCustomEncoder.matches(senha, usuario.senha)) {
            return
        }

        usuario.senha = passwordEncoder.encode(senha)
        usuario.usaCriptografiaAntiga = false

        usuarioGateway.salvarUsuario(usuario)
    }
}
