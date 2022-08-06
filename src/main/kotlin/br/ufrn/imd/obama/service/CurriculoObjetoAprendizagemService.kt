package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CurriculoObjetoAprendizagemService {
    fun buscarPeloCurriculo(
        nome: String,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        pageable: Pageable,
        tipoVisualizacao: String?
    ): Page<ObjetoAprendizagem>
}
