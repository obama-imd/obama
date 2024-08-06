package br.ufrn.imd.obama.planoaula.infrastructure.configuration

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class PlanoAulaConfig {
    @Bean
    @Primary
    fun setUpPlanoAulaUseCase(planoAulaGateway: PlanoAulaGateway): PlanoAulaUseCase {
        return PlanoAulaUseCaseImpl(planoAulaGateway)
    }
}