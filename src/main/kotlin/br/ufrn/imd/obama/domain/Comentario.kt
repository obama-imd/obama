package br.ufrn.imd.obama.domain


import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="comentario", schema="public")
data class Comentario (
	// TODO: A entidade 'Comentario' está com 2 mapeamentos diversos: PlanoDeAula e ObjetoAprendizagem.
	// Rever esses mapeamentos para corrigir as relações entre as entidades envolvidas.

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@ManyToOne
	val usuario: Usuario = Usuario(),

	@Column(columnDefinition="text", name="texto")
	val texto: String = "",

	@Column(name="data_comentario")
	val dataComentario: LocalDateTime = LocalDateTime.now(),

	@Column(nullable=true)
	val deletado: Boolean = false,

	@Column(name="comentario_de_feedback")
	val comentarioDeFeedback: Boolean = false
)
