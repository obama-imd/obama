package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.NivelEnsino

interface NivelEnsino {
    fun listarNiveisEnsino(): Set<NivelEnsino>
}
