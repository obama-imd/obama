package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AnoEnsinoRepository : JpaRepository<AnoEnsinoEntity, Long> {
    //TODO: o parâmetro deveria ser Long, ou seja, ele não deveria ser nullable. Não faz sentido no contexto de um banco de dados relacional,
    // pois o ID de uma entidade (chave primária) nunca deve ser null. Passar null para findById geralmente resulta em um comportamento indefinido
    // ou uma consulta que não retorna resultados. Modifique isso, em vários locais está sendo usado Long? como parâmetro.
    fun findById(id: Long?): Optional<AnoEnsinoEntity>
}
