package br.ufrn.imd.obama.enums;

enum class StatusSubmissao(
	val id: Int,
	val denominacao: String
) {
	AGUARDADO_REVISAO(1,"Aguardando revisão"),
	NECESSARIO_AJUSTE(2,"Necessário ajuste"),
	VALIDADO(3,"Validado"),
	RASCUNHO(4,"Rascunho"),
	REMOVIDO(5,"Removido"),
	SUGESTAO_REJEITADA (6,"Sugestão rejeitada"),
	EM_REVISAO (7,"Em revisão"),
	PUBLICADO (8,"PUBLICADO"),
	AGUARDANDO_REVISAO_FINAL(9,"AGUARDANDO REVISAO FINAL");
}
