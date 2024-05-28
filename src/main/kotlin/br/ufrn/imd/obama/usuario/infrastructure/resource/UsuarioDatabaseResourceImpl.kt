package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioDatabaseUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.tags.Tag
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
    private val usuarioDatabaseUseCase: UsuarioDatabaseUseCase
):UsuarioDatabaseResource {

    @PostMapping("/cadastrar")
    override fun salvarUsuario(
        @RequestBody usuario: Usuario
    ): ResponseEntity<UsuarioResponse> {
        val encryptPassword: String = BCryptPasswordEncoder().encode(usuario.senha)

        val obj = Usuario(
            id = 0L,
            nome = usuario.nome,
            sobrenome = usuario.sobrenome,
            email = usuario.email,
            senha = encryptPassword,
            papel = Papel.PADRAO,
            ativo = usuario.ativo,
            tipoCadastro = usuario.tipoCadastro,
            token = usuario.token,
        )

        val usuarioSalvo = usuarioDatabaseUseCase.salvarUsuario(obj)

        return ResponseEntity.ok(usuarioSalvo.toResponse())
    }
}
