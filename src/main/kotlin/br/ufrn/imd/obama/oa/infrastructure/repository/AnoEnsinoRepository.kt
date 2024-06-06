package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnoEnsinoRepository : JpaRepository<AnoEnsinoEntity, Long> {
}
