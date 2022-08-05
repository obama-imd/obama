package br.ufrn.imd.obama.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name="objetoaprendizagem_avaliacao", schema="public")
data class AvaliacaoOA  (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="usuario_id")
	val usuario: Usuario = Usuario(),

	@Column(name = "data_avaliacao")
	val dataAvaliacao: LocalDateTime = LocalDateTime.now(),

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="objeto_aprendizagem_id")
	val objetoAprendizagem: ObjetoAprendizagem = ObjetoAprendizagem()
)
