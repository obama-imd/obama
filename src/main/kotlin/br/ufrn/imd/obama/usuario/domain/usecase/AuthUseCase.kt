package br.ufrn.imd.obama.usuario.domain.usecase

interface AuthUseCase {
    fun gerarToken(login: String, senha: String): String

    fun gerarRefreshToken(login: String, senha: String): String

    fun atualizarAccessToken(accessToken: String): String
}
