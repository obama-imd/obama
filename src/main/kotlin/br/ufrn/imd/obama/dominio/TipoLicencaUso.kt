package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="tipo_licenca_uso", schema="public")
data class TipoLicencaUso (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long,

	@Column(name = "nome")
	val nome: String,

	@Column(name = "versao")
	val versao: String,
)
