package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.exception.AutorMantenedorNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DescritorNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.HabilidadeNaoEncontradaException
import br.ufrn.imd.obama.oa.domain.exception.IdiomaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.ObjetoAprendizagemPlataformaNaoEncontradaException
import br.ufrn.imd.obama.oa.domain.exception.TipoLicensaUsoNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.gateway.AutorMantenedorGateway
import br.ufrn.imd.obama.oa.domain.gateway.DescritorDatabaseGateway
import br.ufrn.imd.obama.oa.domain.gateway.HabilidadeGateway
import br.ufrn.imd.obama.oa.domain.gateway.IdiomaGateway
import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemGateway
import br.ufrn.imd.obama.oa.domain.gateway.ObjetoAprendizagemPlataformaGateway
import br.ufrn.imd.obama.oa.domain.gateway.TipoLicensaUsoGateway
import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor
import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.domain.model.Idioma
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import br.ufrn.imd.obama.oa.domain.model.builder.ObjetoAprendizagemBuilder
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate


class ObjetoAprendizagemUseCaseImpl(
    private val oaGateway: ObjetoAprendizagemGateway,
    private val autorMantenedorGateway: AutorMantenedorGateway,
    private val descritorGateway: DescritorDatabaseGateway,
    private val habilidadeGateway: HabilidadeGateway,
    private val plataformaGateway: ObjetoAprendizagemPlataformaGateway,
    private val tipoLicensaUsoGateway: TipoLicensaUsoGateway,
    private val idiomaGateway: IdiomaGateway
): ObjetoAprendizagemUseCase {

//    private val CURRICULO_DATABASE_GATEWAY_ADAPTER_SUFIXO = "OADatabaseGatewayAdapter"

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun buscarPorId(
        id: Long
    ): ObjetoAprendizagem {
        logger.info("method={}; id={};", "buscarPorId", id)

        return oaGateway.procurarPorID(id)
    }

    override fun buscarPorParametros(
        pageable: Pageable,
        nome: String?,
        tipoAcesso: TipoAcesso?,
        nivelEnsinoId: Long?,
        temaConteudoId: Long?,
        descritorId: Long?,
        anoEnsinoId: Long?,
        habilidadeId: Long?
    ): Page<ObjetoAprendizagem> {
        logger.info("method={};", "buscarPorParametros")

        return oaGateway.procurarPorNomeETipoAcessoENivelEnsinoIdETemaConteudoIdEDescritorIdEAnoEnsinoIdEHabilidadeId(
            pageable = pageable,
            nome = nome,
            tipoAcesso = tipoAcesso,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            descritorId = descritorId,
            anoEnsinoId = anoEnsinoId,
            habilidadeId = habilidadeId
        )
    }

    override fun cadastrarObjetoAprendizagem(
        nome: String,
        descricao: String,
        quantidadeAcessos: Int,
        thumbnailPath: String?,
        dataLancamento: LocalDate?,
        versao: String?,
        ativo: Boolean,
        tipoLicensaUsoId: Long,
        idiomaIds: Set<Long>,
        autorMantenedorIds: Set<Long>,
        descritorIds: Set<Long>,
        habilidadeIds: Set<Long>,
        plataformas: List<Long>
    ): ObjetoAprendizagem {
        logger.info("nome={}; descricao={}; quantidadeAcessos={}; thumbnailPath={}; dataLancamento={}; versao={}; ativo={}; tipoLicensaUsoId={}; idiomaIds={}; autorMantenedorIds={}; descritorIds={}; habilidadeIds={}; plataformas.size={}",
            nome, descricao, quantidadeAcessos, thumbnailPath, dataLancamento, versao, ativo, tipoLicensaUsoId, idiomaIds.size, autorMantenedorIds.size, descritorIds.size, habilidadeIds.size, plataformas.size)

        val tipoLicensaUso = tipoLicensaUsoGateway.buscarPorId(tipoLicensaUsoId)
            ?: throw TipoLicensaUsoNaoEncontradoException("Tipo de licença de uso com ID $tipoLicensaUsoId não encontrado")

        val idiomas = idiomaIds.map { idiomaId ->
            idiomaGateway.buscarPorId(idiomaId)
                ?: throw IdiomaNaoEncontradoException("Idioma com ID $idiomaId não encontrado")
        }.toSet()

        val autoresMantenedores = autorMantenedorIds.map { autorId ->
            autorMantenedorGateway.buscarPorId(autorId)
                ?: throw AutorMantenedorNaoEncontradoException("Autor/Mantenedor com ID $autorId não encontrado")
        }.toSet()

        val descritores = descritorIds.map { descritorId ->
            descritorGateway.buscarPorId(descritorId)
                ?: throw DescritorNaoEncontradoException("Descritor com ID $descritorId não encontrado")
        }.toSet()

        val habilidades = habilidadeIds.map { habilidadeId ->
            habilidadeGateway.buscarPorId(habilidadeId)
                ?: throw HabilidadeNaoEncontradaException("Habilidade com ID $habilidadeId não encontrada")
        }.toSet()

        val plataformas = plataformas.map { plataformaId ->
            plataformaGateway.buscarPorId(plataformaId)
                ?: throw ObjetoAprendizagemPlataformaNaoEncontradaException("Plataforma com ID $plataformaId não encontrada")
        }

        if (quantidadeAcessos < 0) {
            throw IllegalArgumentException("Quantidade de acessos não pode ser negativa")
        }

        val objetoAprendizagem = construirObjetoAprendizagem(
            nome, descricao, quantidadeAcessos, thumbnailPath, dataLancamento, versao, ativo,
            tipoLicensaUso, idiomas, autoresMantenedores, descritores, habilidades, plataformas
        )

        return oaGateway.cadastrarObjetoAprendizagem(objetoAprendizagem)
    }

    private fun construirObjetoAprendizagem(
        nome: String,
        descricao: String,
        quantidadeAcessos: Int,
        thumbnailPath: String?,
        dataLancamento: LocalDate?,
        versao: String?,
        ativo: Boolean,
        tipoLicensaUso: TipoLicensaUso?,
        idiomas: Set<Idioma>?,
        autoresMantenedores: Set<AutorMantenedor>,
        descritores: Set<Descritor>,
        habilidades: Set<Habilidade>,
        plataformas: List<ObjetoAprendizagemPlataforma>
    ): ObjetoAprendizagem {
        return ObjetoAprendizagemBuilder(
            id = 0,
            nome = nome,
            descricao = descricao,
            quantidadeAcessos = quantidadeAcessos,
            ativo = ativo,
            autoresMantenedores = autoresMantenedores,
            descritores = descritores,
            habilidades = habilidades,
            plataformas = plataformas
        )
            .thumbnailPath(thumbnailPath)
            .dataLancamento(dataLancamento)
            .versao(versao)
            .tipoLicensaUso(tipoLicensaUso)
            .idiomas(idiomas)
            .build()
    }
}
