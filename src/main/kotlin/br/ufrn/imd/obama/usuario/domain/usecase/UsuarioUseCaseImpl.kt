package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.exception.SenhaInvalidaException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.server.ResponseStatusException
import java.util.*

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

    override fun montarNovoUsuario(request: CadastrarUsuarioRequest): Usuario {
        if (request.senha.length < 8) {
            throw SenhaInvalidaException("A senha deve ter pelo menos 8 caracteres")
        }

        return Usuario(
            nome = request.nome,
            sobrenome = request.sobrenome,
            email = request.email,
            senha = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(request.senha),
            papel = Papel.PADRAO,
            ativo = false,
            tipoCadastro = TipoCadastro.PADRAO,
            token = UUID.randomUUID().toString()
        )
    }
}
