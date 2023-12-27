package br.ufrn.imd.obama.oa.util

import br.ufrn.imd.obama.oa.domain.model.Curriculo

fun criarCurriculo(): Curriculo {
    return Curriculo(1L, "BNCC", "Teste")
}

val NOME_BNCC_CURRICULO = "BNCC"

val NOME_CURRICULO_INVALIDO = "I%@"
