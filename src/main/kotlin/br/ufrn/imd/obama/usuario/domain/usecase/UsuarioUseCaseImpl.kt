package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.*

class UsuarioUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway
): UsuarioUseCase {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        /*
            Todo: É necessário adicionar uma verificação a mais aqui. E-mail é um campo único no banco de dados.
             Logo se já tiver um usuário com o e-mail informado precisa lançar uma exceção e a aplicação precisa entender um 400.
        */

        return usuarioGateway.salvarUsuario(usuario)
    }
}
