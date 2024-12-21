package br.ufrn.imd.obama.oa.infrastructure.resource

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import br.ufrn.imd.obama.oa.domain.usecase.ObjetoAprendizagemUseCase
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.mapper.toBuscarOaResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaIdResponse
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.BuscarOaResponse
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    description = "Rotas para objetos de aprendizagem"
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
        @RequestParam("nivelEnsinoId", required = false) nivelEnsinoId: Long?,
        @RequestParam("temaConteudoId", required = false) temaConteudoId: Long?,
        @RequestParam("descritorId", required = false) descritorId: Long?,
        @RequestParam("habilidadeId", required = false) habilidadeId: Long?,
        @RequestParam("tipoAcesso", required = false) tipoAcesso: TipoAcesso?
    ): Page<BuscarOaResponse> {
        logger.info("method={};", "buscarPorParametros")

        return objetoAprendizagemUseCase.buscarPorParametros(
            pageable,
            nome,
            nivelEnsinoId,
            temaConteudoId,
            descritorId,
            habilidadeId,
            tipoAcesso
        ).map { it.toBuscarOaResponse() }
    }

    @PostMapping(path = ["/{id}/favoritar"])
    override fun favoritarPorId(
        @AuthenticationPrincipal usuarioDetails: UserDetails,
        @PathVariable("id", required = true) id: Long
    ): ResponseEntity<Void> {
        val usuarioEntity = usuarioDetails as UsuarioEntity

        objetoAprendizagemUseCase.salverComoFavoritoPorId(
            usuario = usuarioEntity.toModel(),
            oaId = id
        )

        return ResponseEntity.ok().build()
    }

}
