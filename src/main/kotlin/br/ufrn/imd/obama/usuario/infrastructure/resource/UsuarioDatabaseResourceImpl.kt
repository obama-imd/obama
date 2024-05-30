package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.*
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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
    private val usuarioDatabaseUseCase: UsuarioUseCase
):UsuarioDatabaseResource {

    @PostMapping("/cadastrar")
    override fun salvarUsuario(
        @RequestBody request: CadastrarUsuarioRequest
    ): ResponseEntity<UsuarioResponse> {

        val novoUsuario = Usuario(
            nome = request.nome,
            sobrenome = request.sobrenome,
            email = request.email,
            senha = BCryptPasswordEncoder().encode(request.senha),
            papel = Papel.PADRAO,
            ativo = false,
            tipoCadastro = TipoCadastro.PADRAO,
            token = UUID.randomUUID().toString()
        )

        return ResponseEntity.ok(
            usuarioDatabaseUseCase.salvarUsuario(novoUsuario).toResponse()
        )
    }
}
