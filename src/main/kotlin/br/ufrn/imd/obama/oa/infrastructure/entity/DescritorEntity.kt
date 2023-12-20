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
@Table(name="descritor")
class DescritorEntity(
		@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descritor_gen")
	@SequenceGenerator(name="descritor_gen", sequenceName = "sq_descritor_id", allocationSize = 1)
	val id: Long,

	@Column(name = "descricao")
	val descricao: String,

	@Column(name = "codigo")
	val codigo: String,

	@ManyToOne
	@JoinColumn(name="tema_conteudo_id")
	val temaConteudo: TemaConteudoEntity,

	@ManyToOne
	@JoinColumn(name = "nivel_ensino_id")
	val nivelEnsino: NivelEnsinoEntity
){
	@ManyToMany(mappedBy="descritores")
	val objetoAprendizagems: MutableSet<ObjetoAprendizagemEntity> = hashSetOf()
}
