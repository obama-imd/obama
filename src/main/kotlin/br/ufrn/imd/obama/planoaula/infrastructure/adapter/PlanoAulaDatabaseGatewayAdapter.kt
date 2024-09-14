package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.exception.AnoEnsinoNaoExistenteException
import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoExistenteException
import br.ufrn.imd.obama.oa.domain.exception.NivelEnsinoNaoExistenteException
import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.infrastructure.mapper.toModel
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.exception.DuracaoNegativaException
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
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

    private val logger = LoggerFactory.getLogger(javaClass)
    private val tituloPadrao = "Plano sem título"

    override fun salvarPlanoAula(
        usuario: Usuario,
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
            referencias = request.referencias,
            token = null,
            status = StatusPlanoAula.RASCUNHO,
            autor = usuario,

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

    override fun buscarPlanosAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula> {

        val planoAulaPage = planoAulaRepository.buscarPlanosAulaPorTitulo(autor,titulo,pageable).toList()

        return PageImpl(planoAulaPage).map {
                planoAula -> planoAula?.toModel()
        }
    }

    override fun buscarPlanoAulaPorId(id: Long): PlanoAula {
        logger.info("method={}; id={};", "buscarPlanoAulaPorId", id)

        return planoAulaRepository.buscarPlanoAulaPorId(id)?.toModel() ?: throw PlanoAulaNaoEncontradoException("Plano de aula não encontrado por ID: $id")
    }
}
