package br.ufrn.imd.obama.oa.domain.model

import java.time.LocalDate


class ObjetoAprendizagem(

	val id: Long,

	var nome: String,

	var descricao: String,

	var quantidadeAcessos: Int,

	var thumbnailPath: String?,

	val dataLancamento: LocalDate?,

	var versao: String?,

	var ativo: Boolean,

	var tipoLicensaUso: TipoLicensaUso?,

	val idiomas: Set<Idioma>?,

	val autoresMantenedores: Set<AutorMantenedor>,

	val descritores: Set<Descritor>,

	val habilidades: Set<Habilidade>,

	val plataformas: List<ObjetoAprendizagemPlataforma>,
)
