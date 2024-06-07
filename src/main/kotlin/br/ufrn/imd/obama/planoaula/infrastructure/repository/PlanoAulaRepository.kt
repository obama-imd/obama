package br.ufrn.imd.obama.planoaula.infrastructure.repository

import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanoAulaRepository: JpaRepository<PlanoAulaEntity, Long>
