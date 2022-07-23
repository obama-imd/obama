package br.ufrn.imd.obama.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="nivelensino", schema="public")
data class NivelEnsino (
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name = "denominacao")
	val denominacao: String = "",

	@Column(name = "denominacao_abreviada")
	val denominacaoAbreviada: String = "",
)
