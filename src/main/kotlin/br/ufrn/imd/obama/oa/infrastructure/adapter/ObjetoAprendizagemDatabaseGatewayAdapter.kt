package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service("ObjetoAprendizagemDatabaseGatewayAdapter")
class ObjetoAprendizagemDatabaseGatewayAdapter(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): ObjetoAprendizagemGateway {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem {
        logger.info("method={}; id={};", "procurarPorId", id)

        return objetoAprendizagemRepository.buscarPorId(id)?.toModel() ?: throw OANaoEncontradoException("OA nao encontrada por ID: " + id)
    }

    override fun procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdEAnoEnsinoIdEHabilidadeId(
        pageable: Pageable,
        nome: String?,
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?,
    ): Page<ObjetoAprendizagem> {
        logger.info("method={};", "procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId")

        return objetoAprendizagemRepository.buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoETemaConteudoEDescritorEAnoEnsinoHabilidade(
            pageable = pageable,
            nome = nome?.uppercase(),
            tipoAcesso = tipoAcesso,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            descritorId = descritorId,
            anoEnsinoId = anoEnsinoId,
            habilidadeId = habilidadeId
        ).map { it.toModel() }
    }
}
