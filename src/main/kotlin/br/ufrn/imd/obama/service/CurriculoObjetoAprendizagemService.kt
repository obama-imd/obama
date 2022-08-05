package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.request.BuscaOAParametrosRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CurriculoObjetoAprendizagemService {
    fun buscarPeloCurriculo(
        requisicao: BuscaOAParametrosRequest,
        pageable: Pageable
    ): Page<ObjetoAprendizagem>
}
