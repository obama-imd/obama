package br.ufrn.imd.obama.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name= "habilidade", schema="public")
data class Habilidade (

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="descricao", columnDefinition="text")
	val descricao: String = "",

	@Column(name = "conhecimentos", columnDefinition="text")
	val conhecimentos: String = "",

	@Column(name = "codigo")
	val codigo: String = "",

	@ManyToOne
	@JoinColumn(name="nivel_ensino_id")
	val nivelEnsino: NivelEnsino = NivelEnsino(),

	@ManyToOne
	@JoinColumn(name="tema_conteudo_id")
	val temaConteudo: TemaConteudo = TemaConteudo(),

	@OneToOne
	@JoinColumn(name = "ano_ensino_id")
	val anoEnsino: AnoEnsino = AnoEnsino()
) {

	@ManyToMany(mappedBy="descritores")
	val objetosAprendizagem: List<ObjetoAprendizagem> = emptyList()
}
