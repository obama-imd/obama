package br.ufrn.imd.obama.usuario.infrastructure.configuration

import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class UsuarioConfig {

    @Bean
    @Primary
    fun usuarioDatabaseGateway(usuarioRepository: UsuarioRepository): UsuarioDatabaseGateway {
        return UsuarioDatabaseGatewayAdapter(usuarioRepository);
    }
}
