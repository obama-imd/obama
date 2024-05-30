package br.ufrn.imd.obama.usuario.util

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.*

fun criarUsuarioInativo(): Usuario {

    return Usuario(
        "Teste",
        "Teste",
        "Teste",
        "Teste",
        Papel.PADRAO,
        false,
        TipoCadastro.PADRAO,
        "teste"
    )
}
