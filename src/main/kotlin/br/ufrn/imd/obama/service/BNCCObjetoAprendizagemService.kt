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
        requisicao: BuscaOAParametrosRequest,
        pageable: Pageable
    ): Page<ObjetoAprendizagem> {

        return objetoAprendizagemRepository.procureTodosAtivosPorNomeETipoVisualizacaoENivelEnsinoIdETemaConteudoIdEDescritorId(
            nome = requisicao.nome,
            descritorId = requisicao.descritorId,
            nivelEnsinoId = requisicao.nivelEnsinoId,
            temaConteudoId = requisicao.temaConteudoId,
            tipoVisualizacao = requisicao.tipoVisualizacao?.let { TipoVisualizacao.valueOf(it) },
            pageable = pageable
        )
    }

}
