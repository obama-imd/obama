package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.usecase.BuscarOa
import br.ufrn.imd.obama.oa.domain.usecase.BuscarOaImpl
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BuscarOaConfig {

    @Bean
    fun setUpBuscarOa(beanFactory: AbstractBeanFactory): BuscarOa {
        return BuscarOaImpl(
                beanFactory
        )
    }
}
