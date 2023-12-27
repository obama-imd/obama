package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name = "curriculo")
class CurriculoEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curriculo_gen")
	@SequenceGenerator(name="curriculo_gen", sequenceName = "sq_curriculo_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome_abreviado")
	val nomeAbreviado: String,

	@Column(name = "nome_completo")
	val nomeCompleto: String
)
