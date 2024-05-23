package br.ufrn.imd.obama.usuario.domain.exception

class UsuarioNaoEncontradoException(
    override val message: String
): RuntimeException() {
}
