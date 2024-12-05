package br.ufrn.imd.obama.planoaula.domain.gateway

import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PlanoAulaGateway {
    fun buscarPlanosAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>

    fun buscarPlanoAulaPorId(id: Long): PlanoAula

    fun buscarPlanosAulaPorCoautor(
        coautor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula>

    fun salvarPlanoAula(
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
    ): PlanoAula

    fun associarOAEmPlanoAula(
        usuario: UsuarioEntity,
        planoId: Long,
        objetosId: Set<Long>
    ): PlanoAula
}
