package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="nivel_ensino")
class NivelEnsinoEntity(
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nivel_ensino_gen")
	@SequenceGenerator(name="nivel_ensino_gen", sequenceName = "sq_nivel_ensino_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	val nome: String,

	@Column(name = "nome_abreviado")
	val nomeAbreviado: String,
)
