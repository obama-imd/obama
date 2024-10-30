package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AnoEnsinoRepository : JpaRepository<AnoEnsinoEntity, Long> {

    fun findById(id: Long?): Optional<AnoEnsinoEntity?>
}
