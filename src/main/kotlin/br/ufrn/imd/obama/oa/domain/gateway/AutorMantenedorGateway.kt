package br.ufrn.imd.obama.oa.domain.gateway

import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor

interface AutorMantenedorGateway {
    fun buscarPorId(id: Long): AutorMantenedor?
}
