package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PlanoAulaGatewayAdapter(
    private val planoAulaRepository: PlanoAulaRepository
): PlanoAulaGateway {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscarPlanosAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula> {

        val planoAulaPage = planoAulaRepository.buscarPlanosAulaPorTitulo(autor,titulo,pageable).toList()

        return PageImpl(planoAulaPage).map {
            planoAula -> planoAula?.toModel()
        }
    }

    override fun buscarPlanoAulaPorId(id: Long): PlanoAula {
        logger.info("method={}; id={};", "buscarPlanoAulaPorId", id)

        return planoAulaRepository.buscarPlanoAulaPorId(id)?.toModel() ?: throw PlanoAulaNaoEncontradoException("Plano de aula não encontrado por ID: $id")
    }
}
