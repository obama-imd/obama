package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="habilidade")
class HabilidadeEntity (

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidade_gen")
	@SequenceGenerator(name="habilidade_gen", sequenceName = "sq_habilidade_id", allocationSize = 1)
	val id: Long,

	@Column(columnDefinition="text", name = "descricao")
	val descricao: String,

	@Column(columnDefinition="text", name = "conhecimentos")
	val conhecimentos: String,

	@Column(name = "codigo")
	val codigo: String,

	@ManyToOne
	@JoinColumn(name="tema_conteudo_id")
	val temaConteudo: TemaConteudoEntity,

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="ano_ensino_id")
	val anoEnsino: AnoEnsinoEntity
){
	@ManyToMany(mappedBy="habilidades")
	val objetoAprendizagems: MutableSet<ObjetoAprendizagemEntity> = hashSetOf()
}
