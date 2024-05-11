package br.ufrn.imd.obama.usuario.domain.gateway

import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity

interface UsuarioDatabaseGateway {
   fun buscarPorEmail(email: String): UsuarioEntity
}
