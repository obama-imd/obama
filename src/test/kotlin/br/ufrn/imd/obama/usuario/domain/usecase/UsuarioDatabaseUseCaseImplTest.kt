package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.util.criarUsuarioAtivo
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.Optional

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioUseCaseImpl::class, UsuarioDatabaseGatewayAdapter::class])
class UsuarioDatabaseUseCaseImplTest {

    @Autowired
    private lateinit var usuarioUseCaseImpl: UsuarioUseCaseImpl

    @MockBean
    private lateinit var usuarioDatabaseGateway: UsuarioDatabaseGateway

    @Test
    fun `deve salvar usuario corretamente`() {
        val usuario = criarUsuarioInativo()

       `when`(usuarioDatabaseGateway.buscarPorEmail(usuario.email))
           .thenThrow(UsuarioNaoEncontradoException::class.java)
        `when`(usuarioDatabaseGateway.salvarUsuario(usuario))
            .thenReturn(usuario)

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

        Assertions.assertTrue(usuarioEncontrado.isPresent)
        Assertions.assertEquals(usuarioEncontrado.get().token, usuario.token)
    }

    @Test
    fun `deve retornar vazio quando token é inválido`() {
        val tokenInvalido = "tokenInvalido"

        `when`(usuarioDatabaseGateway.buscarPorToken(tokenInvalido))
            .thenReturn(null)

        val usuarioEncontrado = usuarioUseCaseImpl.buscarPorToken(tokenInvalido)

        Assertions.assertFalse(usuarioEncontrado.isPresent)
    }

    @Test
    fun `deve ativar usuario quando usuario é inativo`() {
        val usuario = criarUsuarioInativo()

        `when`(usuarioDatabaseGateway.salvarUsuario(usuario))
            .thenReturn(usuario)

        usuarioUseCaseImpl.ativarUsuario(usuario)

        Assertions.assertTrue(usuario.ativo)
    }

}
