package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import java.util.Optional

interface UsuarioUseCase {
    fun salvarUsuario(usuario: Usuario): Usuario

    fun ativarUsuario(usuario: Usuario): Unit

    fun buscarPorToken(token: String): Optional<Usuario>
}
