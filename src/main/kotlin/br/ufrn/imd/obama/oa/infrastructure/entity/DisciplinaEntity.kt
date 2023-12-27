package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="disciplina")
class DisciplinaEntity (
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplina_gen")
	@SequenceGenerator(name="disciplina_gen", sequenceName = "sq_disciplina_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	val nome: String
)
