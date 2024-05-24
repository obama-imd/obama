package br.ufrn.imd.obama.usuario.infrastructure.adapter

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import br.ufrn.imd.obama.usuario.infrastructure.repository.UsuarioRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
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
}
