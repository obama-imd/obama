package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name="participante", schema="public")
data class Participante (

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name = "nome")
	val nome: String = "",

	@Column(name = "cpf")
	val cpf: String = "",

	@Column(name = "email")
	val email: String = "",

	@Column(name = "tipo_participante")
	val tipoParticipante: String = "",

	@Column(name = "token")
	val token: String = "",

	@ManyToOne
	val atividade: Atividade = Atividade(),
)
