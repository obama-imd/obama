package br.ufrn.imd.obama.enums;

enum class StatusUsuario(
	val id: Int,
	val denominacao: String,
) {
	ATIVO(1, "ATIVO"), 
	INATIVO(2, "INATIVO");
}
