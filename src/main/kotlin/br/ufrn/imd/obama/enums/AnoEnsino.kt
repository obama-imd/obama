package br.ufrn.imd.obama.enums

import br.ufrn.imd.obama.dominio.NivelEnsino

enum class AnoEnsino(
	val id: Long,
	val denominacao: String,
	val idNivelEnsino: Long,
	val denominacaoNivel: String,
){
	ANO_ENSINO(0,"Ano ensino", 0, "Ano ensino"),
	ANO_0(1, "Infantil",NivelEnsino.ID_EDUCACAO_INFANTIL, "EDUCAÇÃO INFANTIL"),
	ANO_1(2, "1º Ano",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS"),
	ANO_2(3, "2º Ano",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS"),
	ANO_3(4, "3º Ano",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS"),
	ANO_4(5, "4º Ano",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS"),
	ANO_5(6, "5º Ano",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS"),
	ANO_6(7, "6º Ano",NivelEnsino.ID_ANOS_FINAIS, "ANOS FINAIS"),
	ANO_7(8, "7º ano",NivelEnsino.ID_ANOS_FINAIS, "ANOS FINAIS"),
	ANO_8(9, "8º Ano",NivelEnsino.ID_ANOS_FINAIS, "ANOS FINAIS"),
	ANO_9(10, "9º Ano",NivelEnsino.ID_ANOS_FINAIS, "ANOS FINAIS"),
	ANO_10(11, "1º Ano",NivelEnsino.ID_ENSINO_MEDIO, "ENSINO MÉDIO"),
	ANO_11(12, "2º Ano",NivelEnsino.ID_ENSINO_MEDIO, "ENSINO MÉDIO"),
	ANO_12(13, "3º Ano",NivelEnsino.ID_ENSINO_MEDIO, "ENSINO MÉDIO"),
	ANO_13(14, "EJA",NivelEnsino.ID_EJA, "EDUCAÇÃO DE JOVENS E ADULTOS"),
	ANO_14(15, "ENSINO TÉC./PROF.",NivelEnsino.ID_ENSINO_TEC_PROF, "ENSINO TÉCNICO/PROFISSIONALIZANTE"),
	ANO_15(16, "ENSINO SUP.",NivelEnsino.ID_ENSINO_SUPERIOR, "ENSINO SUPERIOR"),
	ANO_16(17, "Não Identificado",NivelEnsino.ID_ANOS_INICIAIS, "ANOS INICIAIS");
}
