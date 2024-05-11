package br.ufrn.imd.obama.usuario.domain.model

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro

class Usuario (
        val id: Long,
        val nome: String,
        val sobrenome: String,
        val email: String,
        val senha: String,
        val role: Papel,
        val ativo: Boolean,
        val tipoCadastro: TipoCadastro,
        val token: String,
)
