package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="controleplanodeaula", schema="public")
data class ControlePlanoDeAula (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    val usuario: Usuario,

    @Column(name="token")
    val token: String
)
