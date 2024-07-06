package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.usecase.HabilidadeUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.HabilidadeResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/habilidade")
@Validated
@Tag(
    name = "HabilidadeResource",
    description = "Recurso que lida com habilidades"
)
class HabilidadeResourceImpl(
    private val habilidadeUseCase: HabilidadeUseCase
):HabilidadeResource {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
        @RequestParam("anoEnsinoId", required = false) anoEnsinoId: Long?,
        @RequestParam("temaConteudoId", required = false) temaConteudoId: Long?,
        pageable: Pageable
    ): Page<HabilidadeResponse> {
        return habilidadeUseCase.buscarHabilidadesPorAnoDeEnsinoIdETemaConteudoId(
            anoEnsinoId = anoEnsinoId,
            temaConteudoId = temaConteudoId,
            pageable
        )
            .map { it.toResponse() }
    }

}
