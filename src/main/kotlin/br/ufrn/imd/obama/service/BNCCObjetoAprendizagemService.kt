package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.enums.TipoVisualizacao
import br.ufrn.imd.obama.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.request.BuscaOAParametrosRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service("BNCCObjetoAprendizagemService")
class BNCCObjetoAprendizagemService(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): CurriculoObjetoAprendizagemService {

    override fun buscarPeloCurriculo(
        nome: String,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        pageable: Pageable,
        tipoVisualizacao: String?
    ): Page<ObjetoAprendizagem> {

        return objetoAprendizagemRepository.procureTodosAtivosPorNomeETipoVisualizacaoENivelEnsinoIdETemaConteudoIdEDescritorId(
            nome = nome,
            descritorId = descritorId,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            tipoVisualizacao = tipoVisualizacao?.let { TipoVisualizacao.valueOf(it) },
            pageable = pageable
        )
    }

}
