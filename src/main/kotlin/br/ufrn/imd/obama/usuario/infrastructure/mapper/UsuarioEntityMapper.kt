package br.ufrn.imd.obama.usuario.infrastructure.mapper

import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity

fun UsuarioEntity.toModel(): Usuario {
    val usuario = Usuario(
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
        senha = this.senha,
        papel = this.papel,
        ativo = this.ativo,
        tipoCadastro = this.tipoCadastro,
        token = this.token
    )

    usuario.setId(this.id)
    usuario.usaCriptografiaAntiga = this.usaCriptoGrafiaAntiga

    usuario.oasFavoritos = this.oasFavoritos.map { it.toModel() }.toMutableSet()

    return usuario
}
