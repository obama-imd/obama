package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.DisciplinaDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.DisciplinaUseCase
import br.ufrn.imd.obama.oa.domain.usecase.DisciplinaUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DisciplinaConfig {

    @Bean
    fun setUpDisciplinaUseCase(
        disciplinaGateway: DisciplinaDatabaseGateway
    ): DisciplinaUseCase {
        return DisciplinaUseCaseImpl(disciplinaGateway)
    }
}
