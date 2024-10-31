package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toPlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaBuscarPorIdResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaSalvarRequest
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toModel
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    @PostMapping(path = ["/salvar"], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun salvarPlanoAula(
        @AuthenticationPrincipal usuarioDetails: UserDetails,
        @RequestBody planoAulaSalvarRequest: PlanoAulaSalvarRequest,
    ): ResponseEntity<PlanoAulaResponse> {
        val usuarioEntity = usuarioDetails as UsuarioEntity

        val planoAula = planoAulaUseCase.salvarPlanoAula(
            usuarioEntity.toModel(),
            planoAulaSalvarRequest.escola,
            planoAulaSalvarRequest.idNivelEnsino,
            planoAulaSalvarRequest.disciplinasEnvolvidas,
            planoAulaSalvarRequest.idAnoEnsino,
            planoAulaSalvarRequest.duracaoEmMinutos,
            planoAulaSalvarRequest.titulo,
            planoAulaSalvarRequest.metodologia,
            planoAulaSalvarRequest.objetivosEspecificos,
            planoAulaSalvarRequest.objetivoGeral,
            planoAulaSalvarRequest.avaliacao,
            planoAulaSalvarRequest.referencias
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(planoAula.toResponse())
    }
}
