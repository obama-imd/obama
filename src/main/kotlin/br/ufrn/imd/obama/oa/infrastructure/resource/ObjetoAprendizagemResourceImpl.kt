package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import jakarta.websocket.server.PathParam
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
        "/v1/oa"
)
@Validated
class ObjetoAprendizagemResourceImpl(
        private val objetoAprendizagemUseCase: ObjetoAprendizagemUseCase
): ObjetoAprendizagemResource {
    private val logger = LoggerFactory.getLogger(javaClass)


    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPorId(
        @PathParam("id") id: Long,
        pageable: Pageable): Page<BuscarOaIdResponse> {
        logger.info("method={};", "buscarPorId")

        return objetoAprendizagemUseCase.buscarPorId(
            id,
            pageable
        ).map { it.toBuscarOaIdResponse() }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPorParametros(
            pageable: Pageable,
            @RequestParam("nome", required = true) nome: String,
            @RequestParam("nivelEnsinoId", required = false) nivelEnsinoId: Long?,
            @RequestParam("temaConteudoId", required = false) temaConteudoId: Long?,
            @RequestParam("descritorId", required = false) descritorId: Long?,
            @RequestParam("habilidadeId", required = false) habilidadeId: Long?,
            @RequestParam("tipoAcesso", required = false) tipoAcesso: TipoAcesso?,
            @RequestParam("curriculo", required = true) curriculo: String
    ): Page<BuscarOaResponse> {
        logger.info("method={};", "buscarPorParametros")

        return objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                nivelEnsinoId,
                temaConteudoId,
                descritorId,
                habilidadeId,
                tipoAcesso,
                curriculo
        ).map { it.toBuscarOaResponse() }
    }

}
