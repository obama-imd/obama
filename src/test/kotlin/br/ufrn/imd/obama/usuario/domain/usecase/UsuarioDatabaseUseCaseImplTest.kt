package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import br.ufrn.imd.obama.usuario.infrastructure.configuration.SecurityConfiguration
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import java.util.Optional

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioUseCaseImpl::class, UsuarioDatabaseGatewayAdapter::class])
class UsuarioDatabaseUseCaseImplTest {

    @Autowired
    private lateinit var usuarioUseCaseImpl: UsuarioUseCaseImpl

    @MockBean
    private lateinit var usuarioDatabaseGateway: UsuarioDatabaseGateway

    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `deve salvar usuario corretamente`() {
        val usuario = criarUsuarioInativo()

       `when`(usuarioDatabaseGateway.buscarPorEmail(usuario.email))
           .thenThrow(UsuarioNaoEncontradoException::class.java)
        `when`(usuarioDatabaseGateway.salvarUsuario(usuario))
            .thenReturn(usuario)

        `when`(passwordEncoder.encode(anyString())).thenReturn("ibdfhru210ru0yehvibe0320")

        var usuarioSalvo: Usuario? = null

        assertDoesNotThrow {
            usuarioSalvo = usuarioUseCaseImpl.salvarUsuario(usuario)
        }

        Assertions.assertEquals(usuarioSalvo!!.nome, usuario.nome)
        Assertions.assertEquals(usuarioSalvo!!.email, usuario.email)
        Assertions.assertEquals(usuarioSalvo!!.sobrenome, usuario.sobrenome)
        Assertions.assertNotNull(usuarioSalvo)
    }

    @Test
    fun `deve lançar exceção ao tentar salvar usuário inválido`() {
        val usuarioInvalido = criarUsuarioInativo()

        `when`(usuarioDatabaseGateway.salvarUsuario(usuarioInvalido)).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> {
            usuarioUseCaseImpl.salvarUsuario(usuarioInvalido)
        }
    }

    @Test
    fun `deve lançar a exceção UsuarioExistenteException ao tentar salvar usuario ja existente`() {
        val usuarioInvalido = criarUsuarioInativo()

        `when`(passwordEncoder.encode(anyString())).thenReturn("ibdfhru210ru0yehvibe0320")

        `when`(usuarioDatabaseGateway.buscarPorEmail(usuarioInvalido.email))
            .thenReturn(usuarioInvalido)

        assertThrows<UsuarioExistenteException> {
            usuarioUseCaseImpl.salvarUsuario(usuarioInvalido)
        }
    }

    @Test
    fun `deve retornar usuario quando token é válido`() {
        val usuario = criarUsuarioInativo()

        `when`(usuarioDatabaseGateway.buscarPorToken(usuario.token))
            .thenReturn(usuario)

        val usuarioEncontrado = usuarioUseCaseImpl.buscarPorToken(usuario.token)

        Assertions.assertEquals(usuarioEncontrado.nome, usuario.nome)
        Assertions.assertEquals(usuarioEncontrado.email, usuario.email)
        Assertions.assertEquals(usuarioEncontrado.sobrenome, usuario.sobrenome)
        Assertions.assertEquals(usuarioEncontrado.ativo, usuario.ativo)
        Assertions.assertEquals(usuarioEncontrado.token, usuario.token)
    }

    @Test
    fun `buscarPorToken deve lançar exceção quando o usario não for encontrado`() {
        val tokenInvalido = "tokenInvalido"

        `when`(usuarioDatabaseGateway.buscarPorToken(tokenInvalido))
            .thenThrow(UsuarioNaoEncontradoException::class.java)

        Assertions.assertThrows(UsuarioNaoEncontradoException::class.java) {
            usuarioUseCaseImpl.buscarPorToken(tokenInvalido)
        }
    }

    @Test
    fun `deve ativar usuario quando usuario é inativo`() {
        val usuario = criarUsuarioInativo()

        `when`(usuarioDatabaseGateway.salvarUsuario(usuario))
            .thenReturn(usuario)

        usuarioUseCaseImpl.ativarUsuario(usuario)

        Assertions.assertTrue(usuario.ativo)
    }

    @Test
    fun `deve fazer nada quando o ususario ja esta ativo`() {
        val usuario = criarUsuarioAtivo()

        `when`(usuarioDatabaseGateway.salvarUsuario(usuario))
            .thenReturn(usuario)

        usuarioUseCaseImpl.ativarUsuario(usuario)

        Assertions.assertTrue(usuario.ativo)
    }
}
