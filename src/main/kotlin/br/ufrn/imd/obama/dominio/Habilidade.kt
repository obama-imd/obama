package br.ufrn.imd.obama.dominio

import br.ufrn.imd.obama.enums.AnoEnsino;
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
import javax.persistence.Table


@Entity
@Table(name="habilidade", schema="public")
data class Habilidade (

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long,
	
	@Column(name="descricao", columnDefinition="text")
	val descricao: String,
	
	@Column(name = "conhecimentos", columnDefinition="text")
	val conhecimentos: String,

	@Column(name = "codigo")
	val codigo: String,
	
	@ManyToOne
	@JoinColumn(name="nivelensino")
	val nivelEnsino: NivelEnsino,
	
	@ManyToOne
	@JoinColumn(name="temaconteudo")
	val temaConteudo: TemaConteudo,
	
	@Enumerated(value = EnumType.ORDINAL)
	val anoEnsino: AnoEnsino
) {

	@ManyToMany(mappedBy="descritores")
	val objetosAprendizagem: List<ObjetoAprendizagem> = emptyList()
}
