package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page

interface ObjetoAprendizagemUseCase {

    fun buscarPorId(
        id: Long
    ): ObjetoAprendizagem

    fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        tipoAcesso: TipoAcesso?
    ): Page<ObjetoAprendizagem>

}
