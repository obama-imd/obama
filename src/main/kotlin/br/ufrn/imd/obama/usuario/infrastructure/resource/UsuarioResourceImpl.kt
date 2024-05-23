package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/usuario")
@Validated
class UsuarioResourceImpl (
    private val usuarioUseCase: UsuarioUseCase,
    private val usuarioGatewayAdapter: UsuarioGatewayAdapter
): UsuarioResource {
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun salvarUsuario(
        @RequestBody usuario: Usuario
    ): ResponseEntity<UsuarioResponse> {
        if(usuarioGatewayAdapter.loadUserByUsername(usuario.email) != null)
            return ResponseEntity.badRequest().build()

        val encryptPassword: String = BCryptPasswordEncoder().encode(usuario.senha)

        val obj = Usuario(
            id = 0L,
            nome = usuario.nome,
            sobrenome = usuario.sobrenome,
            email = usuario.email,
            senha = encryptPassword,
            role = usuario.role,
            ativo = usuario.ativo,
            tipoCadastro = usuario.tipoCadastro,
            token = usuario.token,
        )

        return usuarioUseCase.salvarUsuario(obj).let {
            ResponseEntity.ok(it.toResponse())
        }
    }

}