package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

interface TemaConteudoUseCase {

    fun listarTemaConteudos(idCurriculo: Long): Set<TemaConteudo>
}
