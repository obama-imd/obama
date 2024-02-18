package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NivelEnsinoRepository: JpaRepository<NivelEnsinoEntity, Long> {
}
