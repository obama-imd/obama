package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository

class UsuarioDatabaseGatewayAdapter(
    private val usuarioRepository: UsuarioRepository
): UsuarioDatabaseGateway {

    override fun buscarPorEmail(email: String): Usuario {
        return usuarioRepository.findByEmail(email)?.toModel() ?: throw UsuarioNaoEncontradoException("Usuário Não encontrado")
    }
}
