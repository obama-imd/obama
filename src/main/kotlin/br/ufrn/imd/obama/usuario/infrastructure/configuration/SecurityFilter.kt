package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val usuarioDatabaseGateway: UsuarioDatabaseGateway
): OncePerRequestFilter() {



    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = this.recoverToken(request)

        if(!token.isNullOrBlank()) {
            var login = tokenService.validateToken(token)

            val usuario: UserDetails = usuarioDatabaseGateway.buscarPorEmail(login)

            val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)

            //Salva no contexto da aplicação
            SecurityContextHolder.getContext().authentication = authentication
        }

        return filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        if(authHeader.isNullOrBlank()) return null

        return authHeader.replace("Bearer ", "")
    }

}
