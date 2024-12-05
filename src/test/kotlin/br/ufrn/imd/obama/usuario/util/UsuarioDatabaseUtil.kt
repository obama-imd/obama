package br.ufrn.imd.obama.usuario.util

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.*

const val SENHA_PADRAO = "Teste123123"
const val EMAIL1 = "usuario_teste123@ufrn.com"
const val EMAIL2 = "Teste123@ufrn.com"

fun criarUsuarioInativo(): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        "Teste123@ufrn.com",
        "Tes@12346",
        Papel.PADRAO,
        false,
        TipoCadastro.PADRAO,
        UUID.randomUUID().toString()
    )
}

fun criarUsuarioAtivo(email: String? = null): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        email ?: EMAIL1,
        SENHA_PADRAO,
        Papel.PADRAO,
        true,
        TipoCadastro.PADRAO,
        UUID.randomUUID().toString()
    )
}

fun criarUsuarioAtivo(id: Long, email: String? = null): Usuario {
    var usuario = criarUsuarioAtivo(email)
    usuario.setId(id)

    return usuario
}

fun criarUsuarioAtivo(nome: String, email: String, id: Long? = null): Usuario {
    var usuario = Usuario(
        nome,
        "Teste",
        email,
        SENHA_PADRAO,
        Papel.PADRAO,
        true,
        TipoCadastro.PADRAO,
        UUID.randomUUID().toString()
    )

    if (id != null)
        usuario.setId(id)

    return usuario
}

fun criaUsuarioSenhaInvalida(): Usuario {
    val usuario = criarUsuarioInativo()

    usuario.senha = "senha123"

    return usuario
}

