package br.ufrn.imd.obama.planoaula.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.exception.AnoEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.NivelEnsinoNaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.repository.AnoEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.NivelEnsinoRepository
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaDuracaoNegativaException
import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import br.ufrn.imd.obama.planoaula.domain.gateway.PlanoAulaGateway
import br.ufrn.imd.obama.planoaula.domain.model.PlanoAula
import br.ufrn.imd.obama.planoaula.infrastructure.entity.PlanoAulaEntity
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toModel
import br.ufrn.imd.obama.planoaula.infrastructure.repository.PlanoAulaRepository
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoAutorException
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlanoAulaGatewayAdapter(
    private val planoAulaRepository: PlanoAulaRepository,
    private val nivelEnsinoRepository: NivelEnsinoRepository,
    private val anoEnsinoRepository: AnoEnsinoRepository,
    private val disciplinaRepository: DisciplinaRepository,
    private val oaRepository: ObjetoAprendizagemRepository

) : PlanoAulaGateway {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscarPlanosAulaPorTitulo(
        autor: UsuarioEntity,
        titulo: String?,
        pageable: Pageable
    ): Page<PlanoAula> {

        val planoAulaPage = planoAulaRepository.buscarPlanosAulaPorTitulo(autor, titulo, pageable).toList()

        return PageImpl(planoAulaPage).map { planoAula ->
            planoAula?.toModel()
        }
    }

    override fun buscarPlanoAulaPorId(id: Long): PlanoAula {
        logger.info("method={}; id={};", "buscarPlanoAulaPorId", id)

        return planoAulaRepository.buscarPlanoAulaPorId(id)?.toModel()
            ?: throw PlanoAulaNaoEncontradoException("Plano de aula não encontrado por ID: $id")
    }

    override fun salvarPlanoAula(
        usuario: Usuario,
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
        referencias: String?,
    ): PlanoAula {
        logger.info(
            "method={}; escola={}; idNivelEnsino={}; disciplinasEnvolvidas={}; idAnoEnsino={}; duracaoEmMinutos={}; " +
                    "titulo={}; metodologia={}; objetivosEspecificos={}; objetivoGeral={}; referencias={};",
            "salvarPlanoAula",
            escola,
            idNivelEnsino,
            disciplinasEnvolvidas,
            idAnoEnsino,
            duracaoEmMinutos,
            titulo,
            metodologia,
            objetivosEspecificos,
            objetivoGeral,
            referencias
        )

        val nivelEnsino = idNivelEnsino?.let {
            nivelEnsinoRepository.findById(it)
                .orElseThrow { NivelEnsinoNaoEncontradoException("Nível de ensino não encontrado por ID: $idNivelEnsino") }
        }

        val anoEnsino = anoEnsinoRepository.findById(idAnoEnsino)
            .orElseThrow { AnoEnsinoNaoEncontradoException("Ano de ensino não encontrado por ID: $idNivelEnsino") }

        val disciplinasEnvolvidasList = disciplinasEnvolvidas?.mapNotNull {
            disciplinaRepository.findById(it)
                .orElseThrow { DisciplinaNaoEncontradoException("Disciplina não encontrada por ID: $idNivelEnsino") }
        }

        if (duracaoEmMinutos != null && duracaoEmMinutos < 0) throw PlanoAulaDuracaoNegativaException("A duração em minutos não pode ser menor que zero")

        val usuarioEntity = usuario.toEntity()

        val planoAulaEntity = PlanoAulaEntity(
            id = 0,
            dataCadastro = LocalDateTime.now(),
            qtdDownload = 0,
            escola = escola,
            duracaoEmMinutos = duracaoEmMinutos,
            titulo = titulo,
            resumo = null,
            objetivoGeral = objetivoGeral,
            objetivosEspecificos = objetivosEspecificos,
            metodologia = metodologia,
            referencias = referencias,
            token = null,
            status = StatusPlanoAula.RASCUNHO,
            autor = usuarioEntity,
            nivelEnsino = nivelEnsino,
            avaliacao = avaliacao,
            disciplinasEnvolvidas = disciplinasEnvolvidasList ?: listOf(),
            anoEnsino = anoEnsino,
            objetosAprendizagem = mutableSetOf(),
            coautores = setOf()
        )

        if (titulo == null) planoAulaEntity.setTitulo("Plano de aula sem título")

        return planoAulaRepository.save(planoAulaEntity).toModel()
    }

    @Transactional
    override fun associarOAEmPlanoAula(usuario: UsuarioEntity, planoId: Long, objetosId: Set<Long>): PlanoAula {
        var planoAula: PlanoAulaEntity = planoAulaRepository.buscarPlanoAulaPorId(planoId)
            ?: throw PlanoAulaNaoEncontradoException("Plano de aula não encontrado por ID: ${planoId}");

        if (planoAula.getAutor()?.id != usuario.id)
            throw UsuarioNaoAutorException("Usuário de ID ${usuario.id} não é autor do plano de aula de ID ${planoId}");

        var naoEhCoautor: Boolean = planoAula.getCoautores() == null || !(planoAula.getCoautores()!!.contains(usuario));
        if (naoEhCoautor) throw UsuarioNaoAutorException("Usuário de ID ${usuario.id} não é coautor do plano de aula de ID ${planoId}");

        objetosId.forEach { oa ->
            val oaEntity = oaRepository.buscarPorId(oa)
                ?: throw OANaoEncontradoException("Objeto Aprendizagem de ID ${oa} não foi encontrado.")

            planoAula.addOA(oaEntity)
        }

        return planoAulaRepository.save(planoAula).toModel();
    }
}
