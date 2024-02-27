package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TemaConteudoRepository: JpaRepository<TemaConteudoEntity, Long> {
}
