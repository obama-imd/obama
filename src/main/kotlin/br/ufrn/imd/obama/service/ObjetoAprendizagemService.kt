package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.enums.Curriculo
import br.ufrn.imd.obama.request.BuscaOAParametrosRequest
import org.springframework.beans.factory.support.AbstractBeanFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ObjetoAprendizagemService(
    private val beanFactory: AbstractBeanFactory
) {
    companion object {
        private val curriculoPadraoBusca = Curriculo.BNCC.toString()
    }

    fun buscarPorParametros(
        pageable: Pageable,
        requisicao: BuscaOAParametrosRequest,
    ): Page<ObjetoAprendizagem> {
        val nomeCurriculo = requisicao.curriculo

        val prefixoServicoCurriculo = if (!nomeCurriculo.isNullOrBlank()) { nomeCurriculo }
        else { curriculoPadraoBusca }

        Curriculo.valueOf(prefixoServicoCurriculo)

        return ( beanFactory.getBean(
            "${prefixoServicoCurriculo}ObjetoAprendizagemService"
        ) as CurriculoObjetoAprendizagemService).buscarPeloCurriculo(
            requisicao, pageable
        )
    }
}
