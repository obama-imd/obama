package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.usuario.domain.enums.Role
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario

fun criarUsuario(): Usuario {
    return Usuario(
        1L,
        "Teste",
        "Teste",
        "Teste",
        "Teste",
        Role.PADRAO,
        true,
        TipoCadastro.PADRAO,
        "Teste"
    )
}