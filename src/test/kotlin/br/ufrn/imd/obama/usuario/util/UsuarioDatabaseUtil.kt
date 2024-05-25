package br.ufrn.imd.obama.usuario.util

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario

fun criarUsuario(): Usuario {
    return Usuario(
        1L,
        "Teste",
        "Teste",
        "Teste",
        "Teste",
        Papel.PADRAO,
        true,
        TipoCadastro.PADRAO,
        "Teste"
    )
}