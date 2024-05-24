package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.gateway.TemaConteudoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.TemaConteudo
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.TemaConteudoRepository
import java.util.stream.Collectors

class TemaConteudoDatabaseGatewayAdapter(
    private val temaConteudoRepository: TemaConteudoRepository
): TemaConteudoDatabaseGateway {


    override fun listarTemaConteudo(curriculo: Curriculo): Set<TemaConteudo> {

        val stream = temaConteudoRepository.buscarTodosPeloCurriculo(curriculo).stream().map {
            it.toModel()
        }

        return stream.collect(Collectors.toSet())
    }
}
