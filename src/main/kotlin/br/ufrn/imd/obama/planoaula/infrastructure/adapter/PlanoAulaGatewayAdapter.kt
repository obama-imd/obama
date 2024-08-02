package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PlanoAulaGatewayAdapter(
    private val planoAulaRepository: PlanoAulaRepository
): PlanoAulaGateway {
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
}
