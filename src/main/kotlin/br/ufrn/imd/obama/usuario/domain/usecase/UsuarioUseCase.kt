package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.model.Usuario

interface UsuarioUseCase {
    fun salvarUsuario(usuario: Usuario): Usuario

    fun ativarUsuario(usuario: Usuario): Unit

    fun buscarPorToken(token: String): Usuario

    fun alterarCriptografiaSenha(email: String, senha: String)

    fun buscarPorEmail(email: String): Usuario
}
