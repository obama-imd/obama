package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlanoAulaGatewayAdapter(
    private val planoAulaRepository: PlanoAulaRepository,
    private val nivelEnsinoGateway: NivelEnsinoDatabaseGateway
): PlanoAulaGateway {


    override fun salvarPlanoAula(
        escola: String?,
        idNivelEnsino: Long?,
        disciplinasEnvolvidas: List<Long>?,
        idAnoEnsino: Long?,
        duracaoEmMinutos: Int?,
        titulo: String?,
        metodologia: String?,
        objetivosEspecificos: String?,
        objetivoGeral: String?,
        avaliacao: String?,
        referencias: String?
    ): PlanoAula {
        return planoAulaRepository.save(
            PlanoAulaEntity(
                escola = escola,
                nivelEnsino = nivelEnsinoGateway.listarNivelEnsino().find { it.id == idNivelEnsino }?.toEntity(),
                disciplinasEnvolvidas = null, // TODO: adicionar listagem ou busca de disciplina por id
                anoEnsino = null, // TODO: adicionar listagem ou busca de anoEnsino por id
                duracaoEmMinutos = duracaoEmMinutos,
                titulo = titulo,
                metodologia = metodologia,
                objetivosEspecificos = objetivosEspecificos,
                objetivoGeral = objetivoGeral,
                avaliacao = avaliacao,
                referencias = referencias,
                autor = null,
                coautores = null,
                token = null,
                resumo = null,
                dataCadastro = LocalDateTime.now(),
                id = 0,
                status = StatusPlanoAula.RASCUNHO,
                objetosAprendizagem = null,
            )
        ).toModel()
    }

}
