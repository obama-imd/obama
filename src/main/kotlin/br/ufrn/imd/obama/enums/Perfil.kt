package br.ufrn.imd.obama.enums;


enum class Perfil(
	val id: Long,
	val denomincao: String,
) {
	ADMIN(1, "ADMIN"), 
	REVISOR(2, "REVISOR"), 
	PADRAO(3, "PADRAO"),
	PESQUISADOR(4, "PESQUISADOR"),
	REVISOR_DOUTOR(5, "REVISOR_DOUTOR");
}
