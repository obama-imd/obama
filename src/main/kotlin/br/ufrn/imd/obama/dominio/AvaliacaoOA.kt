package br.ufrn.imd.obama.dominio

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name="objetoaprendizagem_avaliacao", schema="public")
data class AvaliacaoOA  (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@ManyToOne
	val usuario: Usuario = Usuario(),

	val dataAvaliacao: LocalDateTime = LocalDateTime.now(),

	@ManyToOne
	val objetoAprendizagem: ObjetoAprendizagem = ObjetoAprendizagem()
)
