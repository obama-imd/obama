package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.gateway.NivelEnsinoDatabaseGateway
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoAutenticadoException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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
        escola: String?,
        idNivelEnsino: Long?,
        disciplinasEnvolvidas: List<Long>?,
        idAnoEnsino: Long?,
        duracaoEmMinutos: Int?,
        titulo: String?,
        resumo: String?,
        metodologia: String?,
        objetivosEspecificos: String?,
        objetivoGeral: String?,
        avaliacao: String?,
        referencias: String?
    ): PlanoAula {

        val usuarioValidado: Usuario?

        try {
            usuarioValidado = usuarioUseCase.buscarPorToken(token)
        } catch (ex: UsuarioNaoEncontradoException) {
            throw UsuarioNaoAutenticadoException(ex.message)
        }

        return planoAulaRepository.save(
            PlanoAulaEntity(
                escola = escola,
                nivelEnsino = nivelEnsinoGateway.listarNivelEnsino().find { it.id == idNivelEnsino }?.toEntity(),
                disciplinasEnvolvidas = disciplinaRepository.findAll().filter { disciplinasEnvolvidas?.contains(it.id) == true },
                anoEnsino = anoEnsinoRepository.findAll().find { it.id == idAnoEnsino },
                duracaoEmMinutos = duracaoEmMinutos,
                titulo = titulo ?: tituloPadrao,
                metodologia = metodologia,
                objetivosEspecificos = objetivosEspecificos,
                objetivoGeral = objetivoGeral,
                avaliacao = avaliacao,
                referencias = referencias,
                autor = usuarioValidado.toEntity(),
                coautores = null,
                token = token,
                resumo = resumo,
                dataCadastro = LocalDateTime.now(),
                id = 0,
                status = StatusPlanoAula.RASCUNHO,
                objetosAprendizagem = null,
            )
        ).toModel()
    }

}
