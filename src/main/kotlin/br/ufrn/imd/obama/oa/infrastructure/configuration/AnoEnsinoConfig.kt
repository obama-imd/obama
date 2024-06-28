package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.AnoEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.AnoEnsinoUseCase
import br.ufrn.imd.obama.oa.domain.usecase.AnoEnsinoUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AnoEnsinoConfig {

    @Bean
    fun setUpAnoEnsinoUseCase(
        anoEnsinoDatabaseGateway: AnoEnsinoDatabaseGateway
    ): AnoEnsinoUseCase {
        return AnoEnsinoUseCaseImpl(anoEnsinoDatabaseGateway);
    }
}
