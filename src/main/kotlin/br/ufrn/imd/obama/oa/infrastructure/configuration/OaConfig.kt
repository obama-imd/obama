package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.ObjetoAprendizagemDatabaseGatewayAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class OaConfig {

    @Bean
    @Primary
    fun setUpBuscarOa(oaGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter
    ): ObjetoAprendizagemUseCase {
        return ObjetoAprendizagemUseCaseImpl(
                oaGatewayAdapter
        )
    }
}
