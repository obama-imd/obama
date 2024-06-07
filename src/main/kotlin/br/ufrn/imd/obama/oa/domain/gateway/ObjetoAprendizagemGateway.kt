package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ObjetoAprendizagemGateway {

    fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem

    fun procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorId(
        pageable: Pageable,
        nome: String?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        tipoAcesso: TipoAcesso?,
    ): Page<ObjetoAprendizagem>
}
