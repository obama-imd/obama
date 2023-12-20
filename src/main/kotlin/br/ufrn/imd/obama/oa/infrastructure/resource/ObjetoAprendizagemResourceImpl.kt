package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.domain.usecase.BuscarOa
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
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
        private val buscarOa: BuscarOa
): ObjetoAprendizagemResource {

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
        return buscarOa.buscarPorParametros(
                pageable,
                nome,
                nivelEnsinoId,
                temaConteudoId,
                temaConteudoId,
                habilidadeId,
                tipoAcesso,
                curriculo
        ).map { it.toBuscarOaResponse() }
    }
}
