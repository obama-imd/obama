package br.ufrn.imd.obama.usuario.infrastructure.entity

import br.ufrn.imd.obama.usuario.domain.enums.Role
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "usuario")
class UsuarioEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_gen")

    @SequenceGenerator(name = "usuario_gen", sequenceName = "sq_usuario_id", allocationSize = 1)
    val id: Long,

    @Column(name = "nome")
    @NotNull
    val nome: String,

    @Column(name = "sobrenome")
    @NotNull
    val sobrenome: String,

    @Column(name = "email")
    @NotNull
    val email: String,

    @Column(name = "senha")
    @NotNull
    val senha: String,

    @Enumerated(EnumType.STRING)

    @Column(name = "role")
    val role: Role,

    @Column(name = "ativo")
    val ativo: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cadastro")
    val tipoCadastro: TipoCadastro,

    @Column(name = "token")
    @NotNull
    val token: String
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (this.role == Role.ADMIN) {
            return mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_PADRAO"), SimpleGrantedAuthority("ROLE_REVISOR"))
        }
        else if(this.role == Role.REVISOR) {
            return mutableListOf(SimpleGrantedAuthority("ROLE_REVISOR"), SimpleGrantedAuthority("ROLE_PADRAO"))
        }
        else {
            return mutableListOf(SimpleGrantedAuthority("ROLE_PADRAO"))
        }
    }

    override fun getPassword(): String {
        return this.senha
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
