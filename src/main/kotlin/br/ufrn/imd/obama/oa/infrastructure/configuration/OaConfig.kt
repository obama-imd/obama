package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCaseImpl
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class OaConfig {

    @Bean
    @Primary
    fun setUpBuscarOa(beanFactory: AbstractBeanFactory): ObjetoAprendizagemUseCase {
        return ObjetoAprendizagemUseCaseImpl(
                beanFactory
        )
    }
}
