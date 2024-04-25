package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface HabilidadeRepository: JpaRepository<HabilidadeEntity, Long> {
    @Query("SELECT h FROM HabilidadeEntity h WHERE " +
            "(:anoEnsinoId IS NULL OR h.anoEnsino.id = :anoEnsinoId)" +
            " AND (:temaConteudoId IS NULL OR h.temaConteudo.id = :temaConteudoId)")
    fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(@Param("anoEnsinoId") anoEnsinoId: Long?
                                                         , @Param("temaConteudoId") temaConteudoId: Long?
                                                         , pageable: Pageable): Page<HabilidadeEntity>
}
