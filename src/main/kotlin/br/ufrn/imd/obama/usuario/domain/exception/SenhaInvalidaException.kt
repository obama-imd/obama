package br.ufrn.imd.obama.usuario.domain.exception

class SenhaInvalidaException(
    override val message: String
): RuntimeException(){}