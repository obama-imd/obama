package br.ufrn.imd.obama.usuario.infrastructure.config

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioGateway
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class UsuarioConfig {
    @Bean
    @Primary
    fun setUpUsuarioUseCase(usuarioGateway: UsuarioGateway): UsuarioUseCase {
        return UsuarioUseCaseImpl(usuarioGateway)
    }
}