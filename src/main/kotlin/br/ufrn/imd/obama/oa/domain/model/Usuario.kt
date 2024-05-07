package br.ufrn.imd.obama.oa.domain.model

import br.ufrn.imd.obama.oa.domain.enums.Role
import br.ufrn.imd.obama.oa.domain.enums.TipoCadastro

class Usuario (
        val id: Long,
        val nome: String,
        val sobrenome: String,
        val email: String,
        val senha: String,
        val role: Role,
        val ativo: Boolean,
        val tipoCadastro: TipoCadastro,
        val token: String,
)
