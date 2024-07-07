package br.ufrn.imd.obama.usuario.domain.exception

class UsuarioExistenteException(
    override val message: String
): RuntimeException()
