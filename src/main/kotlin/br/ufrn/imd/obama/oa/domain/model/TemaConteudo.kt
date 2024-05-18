package br.ufrn.imd.obama.oa.domain.model

import br.ufrn.imd.obama.oa.domain.enums.Curriculo


class TemaConteudo (

	val id: Long,

	val nome: String,

	val disciplina: Disciplina,

	val curriculo: Curriculo,
)
