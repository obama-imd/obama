package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.usecase.HabilidadeUseCase
import br.ufrn.imd.obama.oa.domain.usecase.HabilidadeUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class HabilidadeConfig {

    @Bean
    @Primary
    fun setUpHabilidadeUseCase(habilidadeGateway: HabilidadeGateway): HabilidadeUseCase {
        return HabilidadeUseCaseImpl(habilidadeGateway)
    }
}
