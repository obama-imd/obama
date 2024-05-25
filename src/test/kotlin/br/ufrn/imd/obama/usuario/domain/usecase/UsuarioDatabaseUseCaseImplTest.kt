package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioDatabaseGatewayAdapter
import br.ufrn.imd.obama.usuario.util.criarUsuario
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioDatabaseUseCaseImpl::class, UsuarioDatabaseGatewayAdapter::class])
class UsuarioDatabaseUseCaseImplTest {

    @Autowired
    private lateinit var usuarioDatabaseUseCaseImpl: UsuarioDatabaseUseCaseImpl

    @MockBean
    private lateinit var usuarioDatabaseGatewayAdapter: UsuarioDatabaseGatewayAdapter

    @Test
    fun `deve salvar usuario corretamente`() {
        val usuario = criarUsuario()

        Mockito.`when`(usuarioDatabaseGatewayAdapter.salvarUsuario(usuario)).thenReturn(usuario)

        var usuarioSalvo: Usuario? = null

        assertDoesNotThrow {
            usuarioSalvo = usuarioDatabaseUseCaseImpl.salvarUsuario(usuario)
        }

        Assertions.assertEquals(usuarioSalvo!!.nome, usuario.nome)
        Assertions.assertEquals(usuarioSalvo!!.email, usuario.email)
        Assertions.assertEquals(usuarioSalvo!!.sobrenome, usuario.sobrenome)
        Assertions.assertNotNull(usuarioSalvo)
    }

    @Test
    fun `deve lançar exceção ao tentar salvar usuário inválido`() {
        val usuarioInvalido = criarUsuario()

        Mockito.`when`(usuarioDatabaseGatewayAdapter.salvarUsuario(usuarioInvalido)).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> {
            usuarioDatabaseUseCaseImpl.salvarUsuario(usuarioInvalido)
        }
    }
}