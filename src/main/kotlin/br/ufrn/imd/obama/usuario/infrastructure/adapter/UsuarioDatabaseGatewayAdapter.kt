package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.springframework.stereotype.Component

@Component
class UsuarioDatabaseGatewayAdapter(
    private val usuarioRepository:UsuarioRepository
): UsuarioDatabaseGateway {

    override fun buscarPorEmail(email: String): Usuario {
        val usuario = usuarioRepository.findByEmail(email)
            ?: throw UsuarioNaoEncontradoException("Usuário Não encontrado")

        return usuario.toModel()
    }

    override fun salvarUsuario(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario.toEntity()).toModel()
    }

    override fun buscarPorToken(token: String): Usuario {
        return usuarioRepository.findByToken(token)?.toModel() ?: throw UsuarioNaoEncontradoException("Usuário Não encontrado")
    }
}
