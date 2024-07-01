package br.ufrn.imd.obama.planoaula.infrastructure.config

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PlanoAulaConfig {

    @Bean
    fun setUpPlanoAulaUseCase(
        planoAulaDatabaseGateway: PlanoAulaGateway
    ): PlanoAulaUseCase {
        return PlanoAulaUseCaseImpl(planoAulaDatabaseGateway)
    }

}
