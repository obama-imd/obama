package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.IdiomaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface IdiomaRepository : JpaRepository<IdiomaEntity, Long>{
    fun findById(id: Long?): Optional<IdiomaEntity>
}
