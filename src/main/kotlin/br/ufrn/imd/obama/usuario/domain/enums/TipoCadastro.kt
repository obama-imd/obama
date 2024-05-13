package br.ufrn.imd.obama.usuario.domain.enums

enum class TipoCadastro(val tipoCadastro: String) {
    PADRAO("padrao"),
    GOOGLE("google"),
    FACEBOO("facebook");

    fun getTipoCadastro(): String {
        return tipoCadastro
    }
}
