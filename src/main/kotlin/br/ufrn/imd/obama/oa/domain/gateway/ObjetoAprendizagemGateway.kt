package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ObjetoAprendizagemGateway {

    fun procurarPorID(
        id: Long
    ): ObjetoAprendizagem

    fun procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdEAnoEnsinoIdEHabilidadeId(
        pageable: Pageable,
        nome: String?,
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?,
    ): Page<ObjetoAprendizagem>
}
