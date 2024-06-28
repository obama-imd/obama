package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DisciplinaRepository : JpaRepository<DisciplinaEntity, Long> {

    fun findAllByOrderByNomeAsc(page: Pageable): Page<DisciplinaEntity>
}
