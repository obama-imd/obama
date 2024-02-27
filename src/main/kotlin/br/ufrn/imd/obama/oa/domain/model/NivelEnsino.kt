package br.ufrn.imd.obama.oa.domain.model;

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Setter

@EqualsAndHashCode
@AllArgsConstructor
@Setter
@Getter
class NivelEnsino(

	val id: Long = 0L,

	val nome: String,

	val nomeAbreviado: String,
)
