package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemImpl
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class OaConfig {

    @Bean
    @Primary
    fun setUpBuscarOa(beanFactory: AbstractBeanFactory): ObjetoAprendizagem {
        return ObjetoAprendizagemImpl(
                beanFactory
        )
    }
}
