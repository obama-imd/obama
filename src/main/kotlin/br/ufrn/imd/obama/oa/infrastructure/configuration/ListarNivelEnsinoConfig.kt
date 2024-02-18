package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.ListarNivelEnsino
import br.ufrn.imd.obama.oa.domain.usecase.ListarNivelEnsinoImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.NivelEnsinoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ListarNivelEnsinoConfig {

    @Bean
    fun setUpNivelEnsinoDatabaseGateway(
        nivelEnsinoRepository: NivelEnsinoRepository
    ): NivelEnsinoDatabaseGateway{
        return NivelEnsinoDatabaseGatewayAdapter(nivelEnsinoRepository)
    }

    @Bean
    fun setUpListarNivelEnsino(
        nivelEnsinoDatabaseGateway: NivelEnsinoDatabaseGateway
    ): ListarNivelEnsino {
        return ListarNivelEnsinoImpl(nivelEnsinoDatabaseGateway)
    }
}
