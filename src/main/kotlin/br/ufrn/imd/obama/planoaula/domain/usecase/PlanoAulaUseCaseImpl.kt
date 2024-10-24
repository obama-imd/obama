package br.ufrn.imd.obama.planoaula.domain.usecase

import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class PlanoAulaUseCaseImpl(
    private val planoAulaGateway: PlanoAulaGateway
): PlanoAulaUseCase {
    override fun buscarPlanoAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula> {
        return planoAulaGateway.buscarPlanosAulaPorTitulo(autor,titulo,pageable)
    }

    override fun buscarPlanoAulaPorId(id: Long): PlanoAula {
        return planoAulaGateway.buscarPlanoAulaPorId(id)
    }

    override fun salvarPlanoAula(
        usuario: Usuario,
        escola: String?,
        idNivelEnsino: Long?,
        disciplinasEnvolvidas: List<Long>?,
        idAnoEnsino: Long?,
        duracaoEmMinutos: Int?,
        titulo: String?,
        metodologia: String?,
        objetivosEspecificos: String?,
        objetivoGeral: String?,
        avaliacao: String?,
        referencias: String?,
    ): PlanoAula {
        return planoAulaGateway.salvarPlanoAula(
            usuario,
            escola,
            idNivelEnsino,
            disciplinasEnvolvidas,
            idAnoEnsino,
            duracaoEmMinutos,
            titulo,
            metodologia,
            objetivosEspecificos,
            objetivoGeral,
            avaliacao,
            referencias
        )
    }

}
