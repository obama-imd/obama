package br.ufrn.imd.obama.enums;

enum class TipoVisualizacao(
	val id: Int,
	val denominacao: String,
) {
	DISPOSITIVO_MOVEL(1, "Celular/Tablet"),
	WEB(2,"Online"),
	DESKTOP_EXECUTAVEL(3,"Download");
}
