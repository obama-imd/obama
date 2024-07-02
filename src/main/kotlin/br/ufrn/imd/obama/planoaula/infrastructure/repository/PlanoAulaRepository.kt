package br.ufrn.imd.obama.planoaula.infrastructure.repository

import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PlanoAulaRepository: JpaRepository<PlanoAulaEntity, Long> {
    @Query("SELECT p FROM PlanoAulaEntity p WHERE " +
            "(:titulo IS NULL OR p.titulo = :titulo)")
    fun buscarPlanosAulaPorTitulo(@Param("titulo") titulo: String?, pageable: Pageable): Page<PlanoAulaEntity>
}
