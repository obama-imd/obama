package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.Usuario
import br.ufrn.imd.obama.oa.infrastructure.entity.UsuarioEntity

fun Usuario.toEnitty(): UsuarioEntity {
    return UsuarioEntity(
        id = this.id,
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
        senha = this.senha,
        role = this.role,
        ativo = this.ativo,
        tipoCadastro = this.tipoCadastro,
        token = this.token
    )
}
