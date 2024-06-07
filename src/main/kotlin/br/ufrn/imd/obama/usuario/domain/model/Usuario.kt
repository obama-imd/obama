package br.ufrn.imd.obama.usuario.domain.model

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro

data class Usuario (
        val nome: String,
        val sobrenome: String,
        val email: String,
        var senha: String,
        val papel: Papel,
        val ativo: Boolean,
        val tipoCadastro: TipoCadastro,
        val token: String
) {
        private var id: Long = 0L

        fun setId(id: Long) {
                this.id = id
        }


        fun getId() = this.id

}
