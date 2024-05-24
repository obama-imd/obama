package br.ufrn.imd.obama.oa.infrastructure.repository

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ObjetoAprendizagemRepository: JpaRepository<ObjetoAprendizagemEntity, Long> {

    @Query(
        "SELECT oa FROM ObjetoAprendizagemEntity oa WHERE oa.id = :id"
    )
    fun buscarPorId(@Param("id") id: Long): ObjetoAprendizagemEntity?

    @Query(
            " SELECT distinct oa FROM ObjetoAprendizagemEntity oa " +
                    " LEFT JOIN oa.descritores d " +
                    " LEFT JOIN oa.objetoAprendizagemPlataformas oap " +
                    " WHERE oa.nome like upper(CONCAT('%',:nome,'%'))" +
                    " AND oa.ativo = true" +
                    " AND (:tipoAcesso IS NULL OR oap.tipoAcesso = :tipoAcesso) " +
                    " AND (:nivelEnsinoId IS NULL OR d.nivelEnsino.id = :nivelEnsinoId) " +
                    " AND (:temaConteudoId IS NULL OR d.temaConteudo.id = :temaConteudoId) " +
                    " AND (:descritorId IS NULL OR d.id = :descritorId) ",
    )
    fun buscarTodosAtivoPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
            @Param("nome") nome: String,
            @Param("tipoAcesso") tipoAcesso: TipoAcesso?,
            @Param("nivelEnsinoId") nivelEnsinoId: Long?,
            @Param("temaConteudoId") temaConteudoId: Long?,
            @Param("descritorId") descritorId: Long?,
            pageable: Pageable
    ): Page<ObjetoAprendizagemEntity>
}
