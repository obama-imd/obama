package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import br.ufrn.imd.obama.oa.infrastructure.entity.TipoLicensaUsoEntity

fun TipoLicensaUsoEntity.toModel(): TipoLicensaUso {
    return TipoLicensaUso(
            id = this.id,
            nome = this.nome,
            versao = this.versao,
    )
}
