package br.ufrn.imd.obama.oa.domain.model

import br.ufrn.imd.obama.usuario.domain.model.Usuario
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
) {
	var usuariosFavoritaram: MutableSet<Usuario> = hashSetOf()

	override fun equals(other: Any?): Boolean {
		return id == (other as ObjetoAprendizagem).id
	}
}
