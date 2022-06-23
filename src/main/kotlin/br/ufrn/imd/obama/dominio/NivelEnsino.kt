package br.ufrn.imd.obama.dominio

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
	val id: Long,

	@Column(name = "denominacao")
	val denominacao: String,

	@Column(name = "denominacao_abreviada")
	val denominacaoAbreviada: String,
) {
	companion object {
		val ID_NIVEL_ENSINO_PROVISORIO: Long = -1
		val ID_EDUCACAO_INFANTIL: Long = 1
		val ID_ANOS_INICIAIS: Long = 2
		val ID_ANOS_FINAIS: Long = 3
		val ID_ENSINO_MEDIO: Long = 4
		val ID_EJA: Long = 5
		val ID_ENSINO_TEC_PROF: Long = 6
		val ID_ENSINO_SUPERIOR: Long = 7
	}
}
