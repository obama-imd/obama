package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="ano_ensino")
class AnoEnsinoEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ano_ensino_gen")
	@SequenceGenerator(name="ano_ensino_gen", sequenceName = "sq_ano_ensino_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	val nome: String,

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="nivel_ensino_id")
	val nivelEnsino: NivelEnsinoEntity
)
