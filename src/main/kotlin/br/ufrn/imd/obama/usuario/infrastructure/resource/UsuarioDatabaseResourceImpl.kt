package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.exception.SenhaInvalidaException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import java.util.*
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(
    "/v1/usuario"
)
@Validated
@Tag(
    name = "UsuarioResource",
    description = "Recurso que lida com a manipução do usuário no banco de dados"
)
class UsuarioDatabaseResourceImpl(
    private val usuarioDatabaseUseCase: UsuarioUseCase
):UsuarioDatabaseResource {

    @PostMapping("/cadastrar")
    override fun salvarUsuario(
        @RequestBody request: CadastrarUsuarioRequest
    ): ResponseEntity<UsuarioResponse> {

        try {
            val novoUsuario = usuarioDatabaseUseCase.montarNovoUsuario(request)
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDatabaseUseCase.salvarUsuario(novoUsuario).toResponse())
        }
        catch (e: UsuarioExistenteException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
        }
        catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message, e)
        }
    }
}
