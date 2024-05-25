package br.ufrn.imd.obama.usuario.infrastructure.mapper

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity

fun Usuario.toEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = this.id,
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
        senha = this.senha,
        papel = this.papel,
        ativo = this.ativo,
        tipoCadastro = this.tipoCadastro,
        token = this.token
    )
}
