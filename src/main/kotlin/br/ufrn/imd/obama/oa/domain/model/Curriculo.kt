package br.ufrn.imd.obama.oa.domain.model;

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator


class Curriculo(
	val id: Long,

	val nomeAbreviado: String,

	val nomeCompleto: String
)
