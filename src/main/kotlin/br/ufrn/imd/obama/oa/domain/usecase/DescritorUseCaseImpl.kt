package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.gateway.DescritorDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.Descritor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class DescritorUseCaseImpl(
    private val descritorDatabaseGateway: DescritorDatabaseGateway
): DescritorUseCase {

    override fun listarDescritor(pageable: Pageable): Page<Descritor> {
        return descritorDatabaseGateway.listarDescritores(pageable)
    }
}
