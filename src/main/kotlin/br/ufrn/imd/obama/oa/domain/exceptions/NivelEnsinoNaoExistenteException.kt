package br.ufrn.imd.obama.oa.domain.exceptions

class NivelEnsinoNaoExistenteException(
    override val message: String?
) : RuntimeException() {
}
