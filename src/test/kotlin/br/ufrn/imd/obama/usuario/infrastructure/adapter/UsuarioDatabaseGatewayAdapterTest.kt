package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import br.ufrn.imd.obama.usuario.util.criarUsuarioInativo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioDatabaseGatewayAdapter::class, UsuarioRepository::class])
class UsuarioDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: UsuarioDatabaseGatewayAdapter

    @MockBean
    private lateinit var usuarioRepository: UsuarioRepository

    @Test
    fun `Deve achar um usuário por email`() {


        val usuario = UsuarioEntity(
            1L,
            "Nome",
            "Sobre",
            "teste@teste.com",
            BCryptPasswordEncoder().encode("password"),
            Papel.PADRAO,
            true,
            TipoCadastro.PADRAO,
            "token"
        )

        `when`(
            usuarioRepository.findByEmail(usuario.email)
        ).thenReturn(
            usuario
        )

        assertDoesNotThrow {
            gatewayAdapter.buscarPorEmail(usuario.email)
        }
    }

    @Test
    fun `Não deve achar um usuario por email`() {

        val email = "teste@teste.com"

        `when`(
            usuarioRepository.findByEmail(email)
        ).thenReturn(
            null
        )

        assertThrows<UsuarioNaoEncontradoException> {
            gatewayAdapter.buscarPorEmail(email)
        }
    }

    @Test
    fun `Deve salvar salvar usuario`() {
        val usuario: Usuario = criarUsuarioInativo()

        `when`(usuarioRepository.save(ArgumentMatchers.any() )).thenReturn(usuario.toEntity())

        var usuarioSalvo: Usuario? = null

        assertDoesNotThrow {
            usuarioSalvo = gatewayAdapter.salvarUsuario(usuario)
        }

        Assertions.assertEquals(usuarioSalvo!!.nome, usuario.nome)
        Assertions.assertEquals(usuarioSalvo!!.email, usuario.email)
        Assertions.assertEquals(usuarioSalvo!!.sobrenome, usuario.sobrenome)
        Assertions.assertNotNull(usuarioSalvo)
    }

    @Test
    fun `deve lançar exceção ao tentar salvar usuário inválido`() {
        val usuarioInvalido = criarUsuarioInativo()

        `when`(usuarioRepository.save(usuarioInvalido.toEntity())).thenThrow(RuntimeException::class.java)

        assertThrows<RuntimeException> {
            gatewayAdapter.salvarUsuario(usuarioInvalido)
        }
    }

}
