package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toPlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/planoaula")
@Validated
@Tag(
    name = "PlanoAulaResource",
    description = "Recurso que lida com a manipulação do plano de aula no banco de dados"
)
class PlanoAulaResourceImpl(
    private val planoAulaUseCase: PlanoAulaUseCase
) : PlanoAulaResource {
    @Cacheable(cacheNames = ["planosaula"])
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPlanosAulaPorTitulo(
        @AuthenticationPrincipal usuario: UserDetails,
        @RequestParam(value = "titulo", required = false) titulo: String?,
        pageable: Pageable
    ): Page<PlanoAulaResponse> {

        val autor = usuario as UsuarioEntity

        return planoAulaUseCase.buscarPlanoAulaPorTitulo(autor, titulo, pageable).map { it.toResponse() }
    }

    @GetMapping(path = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPlanoAulaPorId(@PathVariable("id", required = true) id: Long): PlanoAulaBuscarPorIdResponse {
        return planoAulaUseCase.buscarPlanoAulaPorId(id).toPlanoAulaBuscarPorIdResponse()
    }

    @GetMapping(path = ["/buscarPorCoautor"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun buscarPlanosAulaPorCoautor(
        @AuthenticationPrincipal usuario: UserDetails,
        @RequestParam(value = "titulo", required = false) titulo: String?,
        pageable: Pageable
    ): Page<PlanoAulaResponse> {
        val coautor = usuario as UsuarioEntity
        return planoAulaUseCase.buscarPlanosAulaPorCoautor(coautor, titulo, pageable).map { it.toResponse() }
    }
}
