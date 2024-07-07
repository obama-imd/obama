package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.DescritorDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCase
import br.ufrn.imd.obama.oa.domain.usecase.DescritorUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.DescritorDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.repository.DescritorRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class DescritorConfig {

    @Bean
    fun setUpDescritorDatabaseGateway(
        descritorRepository: DescritorRepository
    ): DescritorDatabaseGateway {
        return DescritorDatabaseGatewayAdapter(descritorRepository)
    }

    @Bean
    @Primary
    fun setUpDescritorUseCase(
        descritorDatabaseGateway: DescritorDatabaseGateway
    ): DescritorUseCase {
        return DescritorUseCaseImpl(descritorDatabaseGateway)
    }

}
