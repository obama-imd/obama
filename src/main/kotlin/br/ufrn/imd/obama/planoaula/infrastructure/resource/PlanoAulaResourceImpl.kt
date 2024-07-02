package br.ufrn.imd.obama.planoaula.infrastructure.resource

import br.ufrn.imd.obama.planoaula.domain.usecase.PlanoAulaUseCase
import br.ufrn.imd.obama.planoaula.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaRequest
import br.ufrn.imd.obama.planoaula.infrastructure.resource.exchange.PlanoAulaResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/planoaula")
@Validated
@Tag(
    name = "PlanoAulaResource",
    description = "Recurso que lida com planos de aula"
)
class PlanoAulaResourceImpl(
    private val planoAulaUseCase: PlanoAulaUseCase
): PlanoAulaResource {

    @PostMapping
    override fun criarPlanoAula(
        @RequestHeader("Authorization") header: String,
        @RequestBody planoAula: PlanoAulaRequest
    ): ResponseEntity<PlanoAulaResponse> {

        val token = header.replace("Bearer ", "")

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(planoAulaUseCase.criarPlanoAula(token,planoAula).toResponse())
    }
}
