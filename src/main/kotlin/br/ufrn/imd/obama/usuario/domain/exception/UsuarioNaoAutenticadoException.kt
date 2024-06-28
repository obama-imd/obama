package br.ufrn.imd.obama.usuario.domain.exception

class UsuarioNaoAutenticadoException(
    override val message: String?
) : RuntimeException(){}
