package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import org.springframework.security.crypto.password.PasswordEncoder

class UsuarioUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway,
    private val passwordEncoder: PasswordEncoder,
    private val emailGateway: EmailGateway
): UsuarioUseCase {

    override fun salvarUsuario(usuario: Usuario): Usuario {

        usuario.senha = passwordEncoder.encode(usuario.senha)

        try {
            usuarioGateway.buscarPorEmail(usuario.email)
            throw UsuarioExistenteException("Usuario já existente no banco de dados!")
        } catch (e: UsuarioNaoEncontradoException) {

            val usuarioSalvo = usuarioGateway.salvarUsuario(usuario)

            emailGateway.enviarEmail(
                usuario.email,
                "OBAMA - Ativação de conta",
                gerarTextoAtivacao(usuarioSalvo.token)
            )

            return usuarioSalvo
        }
    }

    fun gerarTextoAtivacao(token: String): String {
        return "Sua conta foi cadastrada com sucesso. Use esta token para ativá-la: $token"
    }
}
