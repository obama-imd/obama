package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.DescritorEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DescritorRepository: JpaRepository<DescritorEntity, Long> {
    fun findAllByOrderByCodigoAsc(page: Pageable): Page<DescritorEntity>
}
