package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.exceptions.AnoEnsinoNaoExistenteException
import br.ufrn.imd.obama.oa.domain.exceptions.DisciplinaNaoExistenteException
import br.ufrn.imd.obama.oa.domain.exceptions.NivelEnsinoNaoExistenteException
import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.exceptions.DuracaoNegativaException
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.collections.*

@Service
class PlanoAulaDatabaseGatewayAdapter(
    private val planoAulaRepository: PlanoAulaRepository,
    private val nivelEnsinoGateway: NivelEnsinoDatabaseGateway,
    private val disciplinaRepository: DisciplinaRepository,
    private val anoEnsinoRepository: AnoEnsinoRepository,
    private val usuarioUseCase: UsuarioUseCase,
): PlanoAulaGateway {

    private val tituloPadrao = "Plano sem título"

    override fun salvarPlanoAula(
        token: String,
        request: PlanoAulaRequest
    ): PlanoAula {

        val duracaoEmMins = request.duracaoEmMinutos
        val titulo = request.titulo
        val disciplinas = request.disciplinasEnvolvidas


        val planoAula = PlanoAula(
            id = 0,
            dataCadastro = LocalDateTime.now(),
            escola = request.escola,

            duracaoEmMinutos =
            if (duracaoEmMins != null && duracaoEmMins < 0) {
                throw DuracaoNegativaException("Duração do plano de aula é inválida!")
            } else duracaoEmMins,

            titulo =
            if (titulo != null && titulo.isBlank()) tituloPadrao else titulo ?: tituloPadrao,

            resumo = request.resumo,
            objetivoGeral = request.objetivoGeral,
            objetivosEspecificos = request.objetivosEspecificos,
            metodologia = request.metodologia,
            avaliacao = request.avaliacao,
            referencias = request.referencias,
            token = null,
            status = StatusPlanoAula.RASCUNHO,
            autor = usuarioUseCase.buscarPorToken(token),

            nivelEnsino = nivelEnsinoGateway.listarNivelEnsino().find
            { it.id == request.idNivelEnsino } ?: throw NivelEnsinoNaoExistenteException(""),

            disciplinasEnvolvidas = disciplinas?.map {
                (disciplinaRepository.findByIdOrNull(it) ?: throw DisciplinaNaoExistenteException("")).toModel()
            } ?: emptyList(),

            anoEnsino = (anoEnsinoRepository.findByIdOrNull(request.idAnoEnsino)
                ?: throw AnoEnsinoNaoExistenteException("")).toModel()
        )

        return planoAulaRepository.save(planoAula.toEntity()).toModel()
    }

}