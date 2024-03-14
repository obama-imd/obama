package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCase
import br.ufrn.imd.obama.oa.domain.usecase.NivelEnsinoUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.NivelEnsinoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class NivelEnsinoConfig {

    @Bean
    fun setUpNivelEnsinoDatabaseGateway(
        nivelEnsinoRepository: NivelEnsinoRepository
    ): NivelEnsinoDatabaseGateway{
        return NivelEnsinoDatabaseGatewayAdapter(nivelEnsinoRepository)
    }

    @Bean
    @Primary
    fun setUpListarNivelEnsino(
        nivelEnsinoDatabaseGateway: NivelEnsinoDatabaseGateway
    ): NivelEnsinoUseCase {
        return NivelEnsinoUseCaseImpl(nivelEnsinoDatabaseGateway)
    }
}
