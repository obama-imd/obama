package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val usuarioDatabaseGateway: UsuarioDatabaseGateway
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return usuarioDatabaseGateway.buscarPorEmail(email = username)
    }

}
