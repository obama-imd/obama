package br.ufrn.imd.obama.usuario.domain.usecase

import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import br.ufrn.imd.obama.usuario.domain.gateway.UsuarioDatabaseGateway
import br.ufrn.imd.obama.usuario.domain.model.Usuario
import java.util.UUID
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UsuarioDatabaseUseCaseImpl(
    private val usuarioGateway: UsuarioDatabaseGateway
): UsuarioDatabaseUseCase {
    override fun salvarUsuario(nome: String, sobrenome: String, email: String, senha: String): Usuario {
        /*
            Todo: É necessário adicionar uma verificação a mais aqui. E-mail é um campo único no banco de dados.
             Logo se já tiver um usuário com o e-mail informado precisa lançar uma exceção e a aplicação precisa entender um 400.
        */

        val encryptPassword: String = BCryptPasswordEncoder().encode(senha)

        val novoUsuario = Usuario(
            id = 0L,
            nome = nome,
            sobrenome = sobrenome,
            email = email,
            senha = encryptPassword,
            papel = Papel.PADRAO,
            ativo = false,
            tipoCadastro = TipoCadastro.PADRAO,
            token = UUID.randomUUID().toString(),
        )

        return usuarioGateway.salvarUsuario(novoUsuario)
    }
}
