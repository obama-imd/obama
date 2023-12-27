package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor

fun criarAutorMantenedor(): AutorMantenedor {
    return AutorMantenedor(
        1L,
        "Autor teste",
        "teste@teste.com",
        "site"
    )
}
