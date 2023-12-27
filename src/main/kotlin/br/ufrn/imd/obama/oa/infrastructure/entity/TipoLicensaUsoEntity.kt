package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="tipo_licensa_uso")
class TipoLicensaUsoEntity (
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_licensa_uso_gen")
	@SequenceGenerator(name="tipo_licensa_uso_gen", sequenceName = "sq_tipo_licensa_uso_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	var nome: String,

	@Column(name = "versao")
	var versao: String,
)
