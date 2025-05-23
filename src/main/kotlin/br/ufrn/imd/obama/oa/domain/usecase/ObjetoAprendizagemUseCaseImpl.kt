package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.adapter.ObjetoAprendizagemDatabaseGatewayAdapter
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


class ObjetoAprendizagemUseCaseImpl(
    private val oaGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter
): ObjetoAprendizagemUseCase {

    private val CURRICULO_DATABASE_GATEWAY_ADAPTER_SUFIXO = "OADatabaseGatewayAdapter"

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscarPorId(
        id: Long
    ): ObjetoAprendizagem {
        logger.info("method={}; id={};", "buscarPorId", id)

        return oaGatewayAdapter.procurarPorID(id)
    }

    override fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?
    ): Page<ObjetoAprendizagem> {
        logger.info("method={};", "buscarPorParametros")

        return oaGatewayAdapter.procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdEAnoEnsinoIdEHabilidadeId(
            pageable = pageable,
            nome = nome,
            tipoAcesso = tipoAcesso,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            descritorId = descritorId,
            anoEnsinoId = anoEnsinoId,
            habilidadeId = habilidadeId
        )
    }
}
