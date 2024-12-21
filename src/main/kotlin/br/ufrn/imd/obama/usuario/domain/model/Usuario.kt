package br.ufrn.imd.obama.usuario.domain.model

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro

data class Usuario (
        val nome: String,
        val sobrenome: String,
        val email: String,
        var senha: String,
        val papel: Papel,
        var ativo: Boolean,
        val tipoCadastro: TipoCadastro,
        val token: String,
        var usaCriptografiaAntiga: Boolean = true
) {

        var oasFavoritos: MutableSet<ObjetoAprendizagem> = hashSetOf()

        private var id: Long = 0L

        fun setId(id: Long) {
                this.id = id
        }


        fun getId() = this.id

}
