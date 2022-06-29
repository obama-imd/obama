package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="disciplina", schema="public")
data class Disciplina  (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="denominacao")
	val denominacao: String = "",
) {
	@Transient
	val temasConteudo: List<TemaConteudo> = emptyList()
}
