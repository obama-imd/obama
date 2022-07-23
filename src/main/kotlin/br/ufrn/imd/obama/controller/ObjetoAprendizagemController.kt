package br.ufrn.imd.obama.controller

import br.ufrn.imd.obama.extension.toBuscaObjetoAprendizagemResponse
import br.ufrn.imd.obama.request.BuscaOAParametrosRequest
import br.ufrn.imd.obama.response.BuscaObjetoAprendizagemResponse
import br.ufrn.imd.obama.service.CurriculoObjetoAprendizagemService
import br.ufrn.imd.obama.service.ObjetoAprendizagemService
import javax.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/oba",
)
@Validated
class ObjetoAprendizagemController(
    private val objetoAprendizagemService: ObjetoAprendizagemService
) {

    @GetMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPorParametros(
        @PageableDefault(page = 0, size = 10, sort = ["name"]) pageable: Pageable,
        @RequestBody @Valid requisicao: BuscaOAParametrosRequest
    ): Page<BuscaObjetoAprendizagemResponse> {


        return objetoAprendizagemService.buscarPorParametros(
            requisicao = requisicao,
            pageable = pageable
        ).map {
            it.toBuscaObjetoAprendizagemResponse()
        }
    }

}
