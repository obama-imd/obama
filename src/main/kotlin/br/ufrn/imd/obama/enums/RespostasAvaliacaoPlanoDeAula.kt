package br.ufrn.imd.obama.enums;

enum class RespostasAvaliacaoPlanoDeAula(
	val id: Long,
	val denominacao: String,
) {
	
	SIM(1,"SIM"),
	NAO(2,"NÃO"),
	NAO_SE_APLICA(3,"NÃO SE APLICA"),
	RAZOAVEL(4,"RAZOÁVEL"),
	PARCIALMENTE(5,"PARCIALMENTE"),
	PUBLICAR(6,"PUBLICAR"),
	MEDIANTE_CORRECOES(7,"PUBLICAR MEDIANTE CORREÇÕES");
}
