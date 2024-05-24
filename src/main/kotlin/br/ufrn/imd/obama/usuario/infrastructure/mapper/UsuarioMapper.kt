package br.ufrn.imd.obama.usuario.infrastructure.mapper

import br.ufrn.imd.obama.usuario.domain.enums.Role
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import com.fasterxml.jackson.annotation.JsonProperty

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

fun Usuario.toResponse(): UsuarioResponse {
    return UsuarioResponse(
        id = this.id,
        nome = this.nome,
        sobrenome = this.sobrenome,
        email = this.email,
        role = this.role,
        ativo = this.ativo,
        tipoCadastro = this.tipoCadastro,
        token = this.token
    )
}
