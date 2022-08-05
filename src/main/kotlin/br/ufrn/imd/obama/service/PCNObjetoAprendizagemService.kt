package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.enums.TipoVisualizacao
import br.ufrn.imd.obama.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.request.BuscaOAParametrosRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service("PCNObjetoAprendizagemService")
class PCNObjetoAprendizagemService(
    private val objetoAprendizagemRepository: ObjetoAprendizagemRepository
): CurriculoObjetoAprendizagemService {

    override fun buscarPeloCurriculo(
        requisicao: BuscaOAParametrosRequest,
        pageable: Pageable
    ): Page<ObjetoAprendizagem> {
        return objetoAprendizagemRepository.procureTodosAtivosPorNomeETipoVisualizacaoENivelEnsinoIdETemaConteudoIdEHabilidadeId(
            pageable = pageable,
            nome = requisicao.nome,
            tipoVisualizacao = requisicao.tipoVisualizacao?.let { TipoVisualizacao.valueOf(it)},
            temaConteudoId = requisicao.temaConteudoId,
            nivelEnsinoId = requisicao.nivelEnsinoId,
            habilidadeId = requisicao.habilidadeId
        )
    }

}
