package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name="descritor", schema="public")
data class Descritor  (
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long,

	@Column(name="descricao")
	val descricao: String,

	@Column(name="codigo")
	val codigo: String,
	
	@ManyToOne
	@JoinColumn(name="nivelensino")
	val nivelEnsino: NivelEnsino,
	
	@ManyToOne
	@JoinColumn(name="temaconteudo")
	val temaConteudo: TemaConteudo,
){
	@ManyToMany(mappedBy="descritores")
	val objetosAprendizagem: List<ObjetoAprendizagem> = emptyList<ObjetoAprendizagem>()
}
