package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.Idioma

interface IdiomaGateway {
    fun buscarPorId(id: Long): Idioma?
}