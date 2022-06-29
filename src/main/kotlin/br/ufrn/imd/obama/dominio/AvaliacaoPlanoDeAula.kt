package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "avaliacao_planodeaula", schema = "public")
class AvaliacaoPlanoDeAula  (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@ManyToOne
	@JoinColumn(name = "id_plano")
	val plano: PlanoDeAula = PlanoDeAula(),

	@ManyToOne
	@JoinColumn(name = "id_revisor")
	val revisor: Usuario = Usuario(),

	@Column(columnDefinition="text")
	val resultadoAvaliacao: String = "",
) {
	@Transient
	val finalizada: Boolean = false
}
