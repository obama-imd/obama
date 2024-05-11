package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.AuthenticationRequest
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/auth"
)
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    //TODO: Remover. Criado de maneira simplificada apenas para facilitar o teste manual.
    private val usuarioRepository: UsuarioRepository
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: AuthenticationRequest): ResponseEntity<*> {
        var usernamePassword = UsernamePasswordAuthenticationToken(request.login, request.senha)

        var auth = authenticationManager.authenticate(usernamePassword)

        return ResponseEntity.ok().body(null)
    }

    //TODO: Remover. Criado de maneira simplificada apenas para facilitar o teste manual.
    @PostMapping("/cadastrar")
    fun cadastrar(): ResponseEntity<*> {

        val novoUsuario = UsuarioEntity(
            0L,
            "Usuario",
            "Teste",
            "usuario@teste.com",
            BCryptPasswordEncoder().encode("1223"),
            Papel.PADRAO,
            true,
            tipoCadastro = TipoCadastro.PADRAO,
            token = UUID.randomUUID().toString()
        )

        usuarioRepository.save(
            novoUsuario
        )
        return ResponseEntity.ok().body(null)
    }
}
