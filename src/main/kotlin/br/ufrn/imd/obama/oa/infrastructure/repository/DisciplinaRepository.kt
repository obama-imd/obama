package br.ufrn.imd.obama.oa.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DisciplinaRepository : JpaRepository<DisciplinaRepository, Long> {

}
