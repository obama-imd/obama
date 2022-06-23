package br.ufrn.imd.obama.dominio


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
	val id: Long,
	
	@ManyToOne
	val usuario: Usuario,

	@Column(columnDefinition="text", name="texto")
	val texto: String,

	@Column(name="data_comentario")
	val dataComentario: LocalDateTime,

	@Column(nullable=true)
	val deletado: Boolean,

	@Column(name="comentario_de_feedback")
	val comentarioDeFeedback: Boolean
)
