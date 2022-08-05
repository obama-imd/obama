package br.ufrn.imd.obama.repository

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.enums.TipoVisualizacao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ObjetoAprendizagemRepository: JpaRepository<ObjetoAprendizagem, Long> {

    @Query(
        " SELECT distinct oa FROM ObjetoAprendizagem oa " +
                " LEFT JOIN oa.descritores d " +
                " LEFT JOIN oa.objetoAprendizagemPlataformas oap " +
                " WHERE lower(oa.nome) like CONCAT('%',:nome,'%') " +
                " AND oa.ativo = true" +
                " AND (:tipoVisualizacao IS NULL OR oap.tipoVisualizacao = :tipoVisualizacao) " +
                " AND (:nivelEnsinoId IS NULL OR d.nivelEnsino.id = :nivelEnsinoId) " +
                " AND (:temaConteudoId IS NULL OR d.temaConteudo.id = :temaConteudoId) " +
                " AND (:descritorId IS NULL OR d.id = :descritorId) ORDER BY oa.nome",
    )
    fun procureTodosAtivosPorNomeETipoVisualizacaoENivelEnsinoIdETemaConteudoIdEDescritorId(
        @Param("nome") nome: String,
        @Param("tipoVisualizacao") tipoVisualizacao: TipoVisualizacao?,
        @Param("nivelEnsinoId") nivelEnsinoId: Long?,
        @Param("temaConteudoId") temaConteudoId: Long?,
        @Param("descritorId") descritorId: Long?,
        pageable: Pageable
    ): Page<ObjetoAprendizagem>


    @Query(
        " SELECT distinct oa FROM ObjetoAprendizagem oa " +
                " LEFT JOIN oa.habilidades h " +
                " LEFT JOIN oa.objetoAprendizagemPlataformas oap " +
                " WHERE lower(oa.nome) like CONCAT('%',:nome,'%') " +
                " AND oa.ativo = true" +
                " AND (:tipoVisualizacao IS NULL OR oap.tipoVisualizacao = :tipoVisualizacao) " +
                " AND (:nivelEnsinoId IS NULL OR h.anoEnsino.nivelEnsino.id = :nivelEnsinoId) " +
                " AND (:temaConteudoId IS NULL OR h.temaConteudo.id = :temaConteudoId) " +
                " AND (:habilidadeId IS NULL OR h.id = :habilidadeId) ORDER BY oa.nome",
    )
    fun procureTodosAtivosPorNomeETipoVisualizacaoENivelEnsinoIdETemaConteudoIdEHabilidadeId(
        @Param("nome") nome: String,
        @Param("tipoVisualizacao") tipoVisualizacao: TipoVisualizacao?,
        @Param("nivelEnsinoId") nivelEnsinoId: Long?,
        @Param("temaConteudoId") temaConteudoId: Long?,
        @Param("habilidadeId") habilidadeId: Long?,
        pageable: Pageable
    ): Page<ObjetoAprendizagem>
}
