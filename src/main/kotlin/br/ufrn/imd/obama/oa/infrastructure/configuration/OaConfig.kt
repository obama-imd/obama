package br.ufrn.imd.obama.oa.infrastructure.configuration

import br.ufrn.imd.obama.oa.domain.gateway.AutorMantenedorGateway
import br.ufrn.imd.obama.oa.domain.gateway.DescritorDatabaseGateway
import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.gateway.IdiomaGateway
import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemPlataformaGateway
import br.ufrn.imd.obama.oa.domain.gateway.TipoLicensaUsoGateway
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
    fun setUpBuscarOa(
        oaGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter,
        autorMantenedorGateway: AutorMantenedorGateway,
        descritorGateway: DescritorDatabaseGateway,
        habilidadeGateway: HabilidadeGateway,
        plataformaGateway: ObjetoAprendizagemPlataformaGateway,
        tipoLicensaUsoGateway: TipoLicensaUsoGateway,
        idiomaGateway: IdiomaGateway
    ): ObjetoAprendizagemUseCase {
        return ObjetoAprendizagemUseCaseImpl(
            oaGateway = oaGatewayAdapter,
            autorMantenedorGateway = autorMantenedorGateway,
            descritorGateway = descritorGateway,
            habilidadeGateway = habilidadeGateway,
            plataformaGateway = plataformaGateway,
            tipoLicensaUsoGateway = tipoLicensaUsoGateway,
            idiomaGateway = idiomaGateway
        )
    }
}
