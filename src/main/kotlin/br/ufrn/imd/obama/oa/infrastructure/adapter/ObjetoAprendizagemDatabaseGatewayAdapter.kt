package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("ObjetoAprendizagemDatabaseGatewayAdapter")
class ObjetoAprendizagemDatabaseGatewayAdapter(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): ObjetoAprendizagemGateway  {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem {
        logger.info("method={};", "procurarPorId")
        logger.info("id={};", id)

        return objetoAprendizagemRepository.buscarPorId(id)?.toModel() ?: throw OANaoEncontradoException("OA nao encontrada por ID: " + id)
    }
}
