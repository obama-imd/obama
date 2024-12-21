package br.ufrn.imd.obama.usuario.infrastructure.entity

import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.usuario.domain.enums.Papel
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name = "usuario")
class UsuarioEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_gen")
    @SequenceGenerator(name = "usuario_gen", sequenceName = "usuario_seq_id", allocationSize = 1)
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
    @Column(name = "papel")
    val papel: Papel,

    @Column(name = "ativo")
    val ativo: Boolean,

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cadastro")
    val tipoCadastro: TipoCadastro,

    @Column(name = "token")
    @NotNull
    val token: String,

    @Column(name="usa_criptografia_antiga")
    @NotNull
    var usaCriptoGrafiaAntiga: Boolean = true,

    @CreationTimestamp
    @Column(name = "data_cadastro")
    val dataCriacao: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    val dataAtualizacao: LocalDateTime? = null
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return if(this.papel == Papel.ADMIN) {
            mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_PADRAO"))
        } else{
            mutableListOf(SimpleGrantedAuthority("ROLE_PADRAO"))
        }
    }

    override fun getPassword(): String {
        return senha
    }

    override fun getUsername(): String {
        return email
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
        return ativo
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuario_objeto_aprendizagem_favorito",
        joinColumns = [JoinColumn(name = "usuario_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")]
    )
    var oasFavoritos: MutableSet<ObjetoAprendizagemEntity> = hashSetOf()

    fun inicializarOasFavoritos() {
        Hibernate.initialize(this.oasFavoritos)
    }
}
