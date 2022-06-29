package br.ufrn.imd.obama.enums;

enum class Curriculo(
	val id: Long,
	val denominacaoAbreviada: String,
	val denominacaoCompleta: String
) {
	PCN(1, "PCN", "Parâmetros Curriculares Nacional"),
	BNCC(2, "BNCC", "Base Nacional Comum CUrricular");
}
