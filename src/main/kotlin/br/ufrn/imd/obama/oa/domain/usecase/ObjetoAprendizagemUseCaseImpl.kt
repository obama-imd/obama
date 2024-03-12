package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


class ObjetoAprendizagemUseCaseImpl(
   private val beanFactory: AbstractBeanFactory
): br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase {

    private val OBJETO_APRENDIZAGEM_DATABASE_GATEWAY_ADAPTER_SUFIXO = "ObjetoAprendizagemDatabaseGatewayAdapter"

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscarPorParametros(
            pageable: Pageable,
            nome: String,
            nivelEnsinoId: Long?,
            temaConteudoId: Long?,
            descritorId: Long?,
            habilidadeId: Long?,
            tipoAcesso: TipoAcesso?,
            curriculo: String
    ): Page<ObjetoAprendizagem> {
        logger.info("method={};", "buscarPorParametros")
        return (
                    beanFactory.getBean(
                        curriculo.plus(OBJETO_APRENDIZAGEM_DATABASE_GATEWAY_ADAPTER_SUFIXO)
                    ) as ObjetoAprendizagemDatabaseGateway
                ).procurarPorCurriculo(
                        pageable,
                        nome,
                        nivelEnsinoId,
                        temaConteudoId,
                        descritorId,
                        habilidadeId,
                        tipoAcesso
                )
    }
}
