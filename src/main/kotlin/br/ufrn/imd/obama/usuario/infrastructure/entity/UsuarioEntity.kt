package br.ufrn.imd.obama.usuario.infrastructure.entity

import br.ufrn.imd.obama.usuario.domain.enums.Role
import br.ufrn.imd.obama.usuario.domain.enums.TipoCadastro
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

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
)
