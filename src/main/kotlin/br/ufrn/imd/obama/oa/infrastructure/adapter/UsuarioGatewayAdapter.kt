package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.UsuarioGateway
import br.ufrn.imd.obama.oa.domain.model.Usuario
import br.ufrn.imd.obama.oa.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEnitty
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioGatewayAdapter (
    private val usuarioRepository: UsuarioRepository
): UsuarioGateway {
    override fun salvarUsuario(usuario: Usuario): Usuario {
        val usuarioEntity: UsuarioEntity = usuario.toEnitty()
        return usuarioRepository.save(usuarioEntity).toModel()
    }
}
