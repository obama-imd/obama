package br.ufrn.imd.obama.oa.domain.model;

enum class TipoAcesso(
	val description: String
) {
	DISPOSITIVO_MOVEL("Smartphone/Tablet"),
	WEB("Web Browser"),
	EXECUTAVEL("Download");
}
