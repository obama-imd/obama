package br.ufrn.imd.obama.usuario.util

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.*

const val SENHA_PADRAO = "Teste123123"

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

fun criarUsuarioAtivo(): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        "usuario_ativo123@ufrn.com",
        SENHA_PADRAO,
        Papel.PADRAO,
        true,
        TipoCadastro.PADRAO,
        UUID.randomUUID().toString()
    )
}

fun criarUsuarioAtivo(id: Long) : Usuario {
    var usuario = criarUsuarioAtivo()
    usuario.setId(id)

    return usuario
}

fun criarUsuarioAtivo(nome: String, email: String, id: Long? = null) : Usuario {
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

    return  usuario
}

