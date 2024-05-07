package br.ufrn.imd.obama.oa.infrastructure.entity

import br.ufrn.imd.obama.oa.domain.enums.Role
import br.ufrn.imd.obama.oa.domain.enums.TipoCadastro
import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class UsuarioEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_gen")
    @SequenceGenerator(name = "usuario_gen", sequenceName = "usuario_seq_id", allocationSize = 1)
    val id: Long,

    @Column(name = "nome")
    val nome: String,

    @Column(name = "sobrenome")
    val sobrenome: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "senha")
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
    val token: String
)