package br.ufrn.imd.obama.usuario.infrastructure.resource

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCase
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toResponse
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.CadastrarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.AtivarUsuarioRequest
import br.ufrn.imd.obama.usuario.infrastructure.resource.exchange.UsuarioResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RestController
@RequestMapping(
    "/v1/usuario"
)
@Validated
@Tag(
    name = "UsuarioResource",
    description = "Recurso que lida com a manipução do usuário no banco de dados"
)
class UsuarioResourceImpl(
    private val usuarioDatabaseUseCase: UsuarioUseCase
):UsuarioResource {

    @PostMapping("/cadastrar")
    override fun salvarUsuario(
        @RequestBody request: CadastrarUsuarioRequest
    ): ResponseEntity<UsuarioResponse> {

        val novoUsuario = Usuario(
            nome = request.nome,
            sobrenome = request.sobrenome,
            email = request.email,
            senha = request.senha,
            papel = Papel.PADRAO,
            ativo = false,
            tipoCadastro = TipoCadastro.PADRAO,
            token = UUID.randomUUID().toString()
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDatabaseUseCase.salvarUsuario(novoUsuario).toResponse())
    }

    @PatchMapping("/ativar")
    override fun ativarUsuarioPorToken(@RequestBody ativarUsuarioRequest: AtivarUsuarioRequest): ResponseEntity<Void> {
        val obj = usuarioDatabaseUseCase.buscarPorToken(ativarUsuarioRequest.token)
        if(obj.isPresent && obj.get().ativo){
            return ResponseEntity.status(HttpStatus.OK).build()
        }
        else {
            usuarioDatabaseUseCase.ativarUsuario(obj.get())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
    }
}
