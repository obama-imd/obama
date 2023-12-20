package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name="idioma")
class IdiomaEntity (
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idioma_gen")
	@SequenceGenerator(name="idioma_gen", sequenceName = "sq_idioma_id", allocationSize = 1)
	val id: Long = 0L,

	@Column(name = "nome")
	val nome: String
)
