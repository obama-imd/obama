package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface UsuarioDatabaseUseCase {
    fun salvarUsuario(usuario: Usuario): Usuario
}
