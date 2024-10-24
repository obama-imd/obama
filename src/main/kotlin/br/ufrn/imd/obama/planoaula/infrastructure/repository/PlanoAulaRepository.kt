package br.ufrn.imd.obama.planoaula.infrastructure.repository

import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface PlanoAulaRepository: JpaRepository<PlanoAulaEntity, Long> {

    @Query("SELECT p FROM PlanoAulaEntity p WHERE " +
            "(p.titulo IS NULL AND p.status <> 'REMOVIDO' AND p.autor = :autor) OR " +
            "(p.titulo = :titulo AND p.status <> 'REMOVIDO' AND p.autor = :autor)")
    fun buscarPlanosAulaPorTitulo(
        @Param("autor") autor: UsuarioEntity,
        @Param("titulo") titulo: String?,
        pageable: Pageable
    ): Page<PlanoAulaEntity>

    @Query("SELECT p FROM PlanoAulaEntity p WHERE p.id = :id")
    fun buscarPlanoAulaPorId(@Param("id") id: Long): PlanoAulaEntity?

    fun save(planoAula: PlanoAulaEntity): PlanoAulaEntity
}
