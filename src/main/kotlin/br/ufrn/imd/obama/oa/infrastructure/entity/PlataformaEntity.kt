package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name="plataforma")
class PlataformaEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plataforma_gen")
	@SequenceGenerator(name="plataforma_gen", sequenceName = "sq_plataforma_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	val nome: String
){
	@OneToMany(mappedBy = "plataforma")
	val objetoAprendizagemPlataformas: MutableSet<ObjetoAprendizagemPlataformaEntity> = hashSetOf()
}
