package br.ufrn.imd.obama.usuario.util

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.*

fun criarUsuarioInativo(): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        "Teste123@ufrn.com",
        "Teste123123",
        Papel.PADRAO,
        false,
        TipoCadastro.PADRAO,
        "teste"
    )
}

fun criarUsuarioAtivo(): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        "usuario_ativo123@ufrn.com",
        "Teste123123",
        Papel.PADRAO,
        true,
        TipoCadastro.PADRAO,
        "teste"
    )
}

