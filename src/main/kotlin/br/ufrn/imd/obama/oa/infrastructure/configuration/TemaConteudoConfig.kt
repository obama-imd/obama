package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudo
import br.ufrn.imd.obama.oa.domain.usecase.TemaConteudoImpl
import br.ufrn.imd.obama.oa.infrastructure.adapter.TemaConteudoDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

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
    ): TemaConteudo {
        return TemaConteudoImpl(
            temaConteudoDatabaseGateway
        )
    }
}
