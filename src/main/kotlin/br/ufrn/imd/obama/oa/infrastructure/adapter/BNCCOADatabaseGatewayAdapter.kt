package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.CurriculoOADatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service("BNCCOADatabaseGatewayAdapter")
class BNCCOADatabaseGatewayAdapter(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): CurriculoOADatabaseGateway {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun procurarPorCurriculo(
            pageable: Pageable,
            nome: String,
            nivelEnsinoId: Long?,
            temaConteudoId: Long?,
            descritorId: Long?,
            habilidadeId: Long?,
            tipoAcesso: TipoAcesso?
    ): Page<ObjetoAprendizagem> {
        logger.info("method={};", "procurarPorCurriculo")

        return objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoEAnoEnsinoIdETemaConteudoIdEHabilidadeId(
           nome,
           tipoAcesso,
           nivelEnsinoId,
           temaConteudoId,
           descritorId,
           pageable
        ).map {
            it.toModel()
        }
    }
}
