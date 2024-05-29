package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioDatabaseUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/v1/usuario"
)
@Tag(
    name = "UsuarioResource",
    description = "Recurso que lida com a manipução do usuário no banco de dados"
)
class UsuarioDatabaseResourceImpl(
    private val usuarioDatabaseUseCase: UsuarioDatabaseUseCase
):UsuarioDatabaseResource {

    @PostMapping("/cadastrar")
    override fun salvarUsuario(
        @RequestBody request: CadastrarUsuarioRequest
    ): ResponseEntity<UsuarioResponse> {

        val usuarioSalvo = usuarioDatabaseUseCase.salvarUsuario(
            nome = request.nome,
            senha = request.senha,
            email = request.email,
            sobrenome = request.sobrenome
        )

        return ResponseEntity.ok(usuarioSalvo.toResponse())
    }
}
