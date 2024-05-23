package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioGateway
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEnitty
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioGatewayAdapter (
    private val usuarioRepository: UsuarioRepository
): UsuarioGateway, UserDetailsService {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        val usuarioEntity: UsuarioEntity = usuario.toEnitty()
        return usuarioRepository.save(usuarioEntity).toModel()
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username == null) return null
        return usuarioRepository.findByEmail(username)
    }
}
