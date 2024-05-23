package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.util.criarUsuario
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import br.ufrn.imd.obama.usuario.domain.usecase.UsuarioUseCaseImpl
import br.ufrn.imd.obama.usuario.infrastructure.adapter.UsuarioGatewayAdapter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [UsuarioUseCaseImpl::class, UsuarioGatewayAdapter::class])
class UsuarioUseCaseImplTest {
    @Autowired
    private lateinit var usuarioUseCase: UsuarioUseCaseImpl

    @MockBean
    private lateinit var usuarioGateway: UsuarioGatewayAdapter

    @Test
    fun `deve salvar usuario corretamente`() {
        val usuario = criarUsuario()

        Mockito.`when`(usuarioGateway.salvarUsuario(usuario)).thenReturn(usuario)

        var usuarioSalvo: Usuario? = null

        assertDoesNotThrow {
            usuarioSalvo = usuarioUseCase.salvarUsuario(usuario)
        }

        Assertions.assertEquals(usuarioSalvo!!.nome, usuario.nome)
        Assertions.assertEquals(usuarioSalvo!!.email, usuario.email)
        Assertions.assertEquals(usuarioSalvo!!.sobrenome, usuario.sobrenome)
        Assertions.assertNotNull(usuarioSalvo)
    }

}