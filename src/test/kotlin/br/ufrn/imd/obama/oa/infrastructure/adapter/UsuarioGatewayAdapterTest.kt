package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.util.criarUsuario
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioGatewayAdapter
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEnitty
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioGatewayAdapter::class, UsuarioRepository::class])
class UsuarioGatewayAdapterTest {

    @Autowired
    private lateinit var usuarioGatewayAdapter: UsuarioGatewayAdapter

    @MockBean
    private lateinit  var usuarioRepository: UsuarioRepository

    @Test
    fun `Deve salvar salvar usuario`() {
        val usuario = criarUsuario()

        Mockito.`when`(usuarioRepository.save(usuario.toEnitty())).thenReturn(usuario.toEnitty())

        var usuarioSalvo: Usuario? = null

        assertDoesNotThrow {
            usuarioSalvo = usuarioGatewayAdapter.salvarUsuario(usuario)
        }

        Assertions.assertEquals(usuarioSalvo!!.nome, usuario.nome)
        Assertions.assertEquals(usuarioSalvo!!.email, usuario.email)
        Assertions.assertEquals(usuarioSalvo!!.sobrenome, usuario.sobrenome)
        Assertions.assertNotNull(usuarioSalvo)
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {
        val usuario = criarUsuario()

        Mockito.`when`(usuarioRepository.findByEmail(usuario.email)).thenReturn(usuario.toEnitty())

        var usuarioEncontrado: UserDetails? = null

        assertDoesNotThrow {
            usuarioEncontrado = usuarioGatewayAdapter.loadUserByUsername(usuario.email)
        }

        Assertions.assertEquals(usuarioEncontrado!!.username, usuario.email)
        Assertions.assertNotNull(usuarioEncontrado)
    }

    @Test
    fun `Deve fazer busca no repository e não achar nenhum dado`() {
        val usuario = criarUsuario()
        Mockito.`when`(usuarioRepository.findByEmail(usuario.email)).thenReturn(null)

        var usuarioEncontrado: UserDetails? = null

        assertDoesNotThrow {
            usuarioEncontrado = usuarioGatewayAdapter.loadUserByUsername(usuario.email)
        }

        Assertions.assertNull(usuarioEncontrado)
    }
}
