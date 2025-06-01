package br.ufrn.imd.obama.oa.domain.model

import java.time.LocalDate


class ObjetoAprendizagem private constructor(

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
	companion object {
		fun create(
			id: Long,
			nome: String,
			descricao: String,
			quantidadeAcessos: Int,
			thumbnailPath: String?,
			dataLancamento: LocalDate?,
			versao: String?,
			ativo: Boolean,
			tipoLicensaUso: TipoLicensaUso?,
			idiomas: Set<Idioma>?,
			autoresMantenedores: Set<AutorMantenedor>,
			descritores: Set<Descritor>,
			habilidades: Set<Habilidade>,
			plataformas: List<ObjetoAprendizagemPlataforma>
		): ObjetoAprendizagem {
			return ObjetoAprendizagem(
				id,
				nome,
				descricao,
				quantidadeAcessos,
				thumbnailPath,
				dataLancamento,
				versao,
				ativo,
				tipoLicensaUso,
				idiomas,
				autoresMantenedores,
				descritores,
				habilidades,
				plataformas
			)
		}
	}

	override fun equals(other: Any?): Boolean {
		return id == (other as ObjetoAprendizagem).id
	}

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + quantidadeAcessos
        result = 31 * result + ativo.hashCode()
        result = 31 * result + nome.hashCode()
        result = 31 * result + descricao.hashCode()
        result = 31 * result + (thumbnailPath?.hashCode() ?: 0)
        result = 31 * result + (dataLancamento?.hashCode() ?: 0)
        result = 31 * result + (versao?.hashCode() ?: 0)
        result = 31 * result + (tipoLicensaUso?.hashCode() ?: 0)
        result = 31 * result + (idiomas?.hashCode() ?: 0)
        result = 31 * result + autoresMantenedores.hashCode()
        result = 31 * result + descritores.hashCode()
        result = 31 * result + habilidades.hashCode()
        result = 31 * result + plataformas.hashCode()
        return result
    }
}
