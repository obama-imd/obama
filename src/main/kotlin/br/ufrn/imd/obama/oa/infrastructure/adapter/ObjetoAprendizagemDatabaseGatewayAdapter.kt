package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemGateway
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

        return objetoAprendizagemRepository
                    .findById(id).map { it.toModel() }
                    .orElseThrow()
    }
}
