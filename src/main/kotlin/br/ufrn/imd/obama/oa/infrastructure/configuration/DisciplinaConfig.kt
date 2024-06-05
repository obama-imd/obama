package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaGateway
import br.ufrn.imd.obama.oa.domain.usecase.DisciplinaUseCase
import br.ufrn.imd.obama.oa.domain.usecase.DisciplinaUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DisciplinaConfig {

    @Bean
    fun setUpDisciplinaUseCase(
        disciplinaGateway: DisciplinaGateway
    ): DisciplinaUseCase {
        return DisciplinaUseCaseImpl(disciplinaGateway)
    }
}
