package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.EmailGateway
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class UsuarioConfig {

    @Bean
    @Primary
    fun usuarioDatabaseGateway(usuarioRepository: UsuarioRepository): UsuarioDatabaseGateway {
        return UsuarioDatabaseGatewayAdapter(usuarioRepository);
    }

    @Bean
    @Primary
    fun setUpUsuarioUseCase(
        usuarioGateway: UsuarioDatabaseGateway,
        passwordEncoder: PasswordEncoder,
        oldCustomEncoder: OldCustomEncoder,
        emailService: EmailGateway
    ): UsuarioUseCase {
        return UsuarioUseCaseImpl(
            usuarioGateway = usuarioGateway,
            passwordEncoder = passwordEncoder,
            oldCustomEncoder = oldCustomEncoder,
            emailService = emailService
        )
    }
}
