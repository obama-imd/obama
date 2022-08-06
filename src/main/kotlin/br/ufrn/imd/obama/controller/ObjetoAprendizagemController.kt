package br.ufrn.imd.obama.controller

import br.ufrn.imd.obama.extension.toBuscaObjetoAprendizagemResponse
import br.ufrn.imd.obama.response.BuscaObjetoAprendizagemResponse
import br.ufrn.imd.obama.service.ObjetoAprendizagemService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun buscarPorParametros(
        @PageableDefault(page = 0, size = 10, sort = ["nome"]) pageable: Pageable,
        @RequestParam("nome", required = true) nome: String,
        @RequestParam("nivel_ensino_id", required = false) nivelEnsinoId: Long?,
        @RequestParam("tema_conteudo_id", required = false) temaConteudoId: Long?,
        @RequestParam("descritor_id", required = false) descritorId: Long?,
        @RequestParam("habilidade_id", required = false) habilidadeId: Long?,
        @RequestParam("tipo_visualizacao", required = false) tipoVisualizacao: String?,
        @RequestParam("curriculo", required = false)  curriculo: String?
    ): Page<BuscaObjetoAprendizagemResponse> {


        return objetoAprendizagemService.buscarPorParametros(
            nome = nome,
            nivelEnsinoId = nivelEnsinoId,
            temaConteudoId = temaConteudoId,
            descritorId = descritorId,
            habilidadeId = habilidadeId,
            curriculo = curriculo,
            tipoVisualizacao =  tipoVisualizacao,
            pageable = pageable
        ).map {
            it.toBuscaObjetoAprendizagemResponse()
        }
    }

}
