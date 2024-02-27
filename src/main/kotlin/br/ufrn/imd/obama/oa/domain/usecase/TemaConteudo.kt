package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.TemaConteudo

interface TemaConteudo {

    fun listarTemaConteudos(): Set<TemaConteudo>
}
