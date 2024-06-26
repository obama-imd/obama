package br.ufrn.imd.obama.oa.infrastructure.mapper

import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import br.ufrn.imd.obama.oa.infrastructure.entity.TipoLicensaUsoEntity

fun TipoLicensaUso.toEntity() : TipoLicensaUsoEntity {
    return TipoLicensaUsoEntity(
        id = this.id,
        nome = this.nome,
        versao = this.versao
    )
}
