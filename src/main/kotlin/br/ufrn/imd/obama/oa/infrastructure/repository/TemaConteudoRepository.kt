package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.infrastructure.entity.TemaConteudoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TemaConteudoRepository: JpaRepository<TemaConteudoEntity, Long> {

    @Query(
        " SELECT distinct tc FROM TemaConteudoEntity tc " +
                " WHERE tc.curriculo = :curriculo"
    )
    fun buscarTodosPeloCurriculo(
        @Param("curriculo") curriculo: Curriculo
    ): List<TemaConteudoEntity>

}
