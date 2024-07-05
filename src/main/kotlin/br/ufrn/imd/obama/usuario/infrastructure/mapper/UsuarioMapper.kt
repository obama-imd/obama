package br.ufrn.imd.obama.usuario.infrastructure.mapper

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse

fun Usuario.toEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = this.getId(),
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
        senha = this.senha,
        papel = this.papel,
        ativo = this.ativo,
        tipoCadastro = this.tipoCadastro,
        token = this.token,
        usaCriptoGrafiaAntiga = this.usaCriptografiaAntiga
    )
}

fun Usuario.toResponse(): UsuarioResponse {
    return UsuarioResponse(
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
    )
}

