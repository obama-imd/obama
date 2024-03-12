package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCase
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoUseCaseImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TemaConteudoConfig {

    @Bean
    fun setUpTemaConteudoDatabaseGateway(
        temaConteudoRepository: TemaConteudoRepository
    ): TemaConteudoDatabaseGateway {
        return TemaConteudoDatabaseGatewayAdapter(
            temaConteudoRepository
        )
    }

    @Bean
    fun setUpTemaConteudoUseCase(
        temaConteudoDatabaseGateway: TemaConteudoDatabaseGateway
    ): TemaConteudoUseCase {
        return TemaConteudoUseCaseImpl(
            temaConteudoDatabaseGateway
        )
    }
}
