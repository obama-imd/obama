package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.TokenGateway
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenGateway: TokenGateway,
    private val usuarioDatabaseGateway: UsuarioDatabaseGateway
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.recuperarToken(request)

        if(!token.isNullOrBlank()) {
            val login = tokenGateway.validarToken(token)

            val usuario: UserDetails = usuarioDatabaseGateway.buscarPorEmail(login).toEntity()

            val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)

            SecurityContextHolder.getContext().authentication = authentication
        }

        return filterChain.doFilter(request, response)
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        if(authHeader.isNullOrBlank()) return null

        return authHeader.replace("Bearer ", "")
    }

}
