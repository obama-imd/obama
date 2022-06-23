package br.ufrn.imd.obama.dominio

import br.ufrn.imd.obama.enums.StatusSubmissao
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name="sugestao_oa", schema="public")
data class SugestaoOA (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nome")
    val nome: String,

    @ManyToOne
    val usuario: Usuario,

    @Column(name = "descricao", columnDefinition = "text")
    val descricao: String,

    @Column(name = "descritores", columnDefinition = "text")
    val descritores: String,

    @Column(name = "link")
    val link: String,

    @Enumerated(value = EnumType.STRING)
    val status: StatusSubmissao,
)
