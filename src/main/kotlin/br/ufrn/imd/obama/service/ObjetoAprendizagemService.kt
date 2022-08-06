package br.ufrn.imd.obama.service

import br.ufrn.imd.obama.domain.ObjetoAprendizagem
import br.ufrn.imd.obama.enums.Curriculo
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
        nome: String,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        habilidadeId: Long?,
        curriculo: String?,
        tipoVisualizacao: String?
    ): Page<ObjetoAprendizagem> {

        val prefixoServicoCurriculo = if (!curriculo.isNullOrBlank()) { curriculo }
        else { curriculoPadraoBusca }

        Curriculo.valueOf(prefixoServicoCurriculo)

        return ( beanFactory.getBean(
            "${prefixoServicoCurriculo}ObjetoAprendizagemService"
        ) as CurriculoObjetoAprendizagemService).buscarPeloCurriculo(
            nome = nome,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            descritorId = descritorId,
            habilidadeId = habilidadeId,
            tipoVisualizacao =  tipoVisualizacao,
            pageable = pageable
        )
    }
}
