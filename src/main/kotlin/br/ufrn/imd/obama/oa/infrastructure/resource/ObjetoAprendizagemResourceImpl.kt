package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ObjetoAprendizagemRequest
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ObjetoAprendizagemResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        "/v1/oa"
)
@Validated
@Tag(
    name = "ObjetoAprendizagemResource",
    description = "Recurso que lida com objetos de aprendizagem"
)
class ObjetoAprendizagemResourceImpl(
        private val objetoAprendizagemUseCase: ObjetoAprendizagemUseCase
): ObjetoAprendizagemResource {
    private val logger = LoggerFactory.getLogger(javaClass)


    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPorId(
        @PathVariable("id", required = true) id: Long,
        ): ResponseEntity<BuscarOaIdResponse> {

            logger.info("method={};", "buscarPorId")
            logger.info("id={};", id)

            return ResponseEntity.ok(objetoAprendizagemUseCase.buscarPorId(id).toBuscarOaIdResponse())
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPorParametros(
        pageable: Pageable,
        @RequestParam("nome", required = false) nome: String?,
        @RequestParam("tipoAcesso", required = false) tipoAcesso: TipoAcesso?,
        @RequestParam("nivelEnsinoId", required = false) nivelEnsinoId: Long?,
        @RequestParam("temaConteudoId", required = false) temaConteudoId: Long?,
        @RequestParam("descritorId", required = false) descritorId: Long?,
        @RequestParam("anoEnsinoId", required = false) anoEnsinoId: Long?,
        @RequestParam("habilidadeId", required = false) habilidadeId: Long?
    ): Page<BuscarOaResponse> {
        logger.info("method={};", "buscarPorParametros")

        return objetoAprendizagemUseCase.buscarPorParametros(
            pageable,
            nome,
            tipoAcesso,
            nivelEnsinoId,
            temaConteudoId,
            descritorId,
            anoEnsinoId,
            habilidadeId
        ).map { it.toBuscarOaResponse() }
    }

    @PostMapping(path = ["/salvar"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun cadastrarObjetoAprendizagem(
        @RequestBody objetoAprendizagem: ObjetoAprendizagemRequest
    ): ResponseEntity<ObjetoAprendizagemResponse> {
        logger.info("method={};", "cadastrarObjetoAprendizagem")
        logger.info("request={};", objetoAprendizagem)

        val objetoAprendizagem = objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
            nome = objetoAprendizagem.nome,
            descricao = objetoAprendizagem.descricao,
            quantidadeAcessos = objetoAprendizagem.quantidadeAcessos,
            thumbnailPath = objetoAprendizagem.thumbnailPath,
            dataLancamento = objetoAprendizagem.dataLancamento,
            versao = objetoAprendizagem.versao,
            ativo = objetoAprendizagem.ativo,
            tipoLicensaUsoId = objetoAprendizagem.tipoLicensaUsoId,
            idiomaIds = objetoAprendizagem.idiomasIds,
            autorMantenedorIds = objetoAprendizagem.autorMantenedorIds,
            descritorIds = objetoAprendizagem.descritorIds,
            habilidadeIds = objetoAprendizagem.habilidadeIds,
            plataformas = objetoAprendizagem.plataformaIds
        )

        val response = objetoAprendizagem.toResponse()

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }

}
