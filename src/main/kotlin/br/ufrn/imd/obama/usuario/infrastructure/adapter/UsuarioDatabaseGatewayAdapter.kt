package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository

class UsuarioDatabaseGatewayAdapter(
    private val usuarioRepository: UsuarioRepository
): UsuarioDatabaseGateway {

    override fun buscarPorEmail(email: String): UsuarioEntity {
        return usuarioRepository.findByEmail(email)
    }
}
