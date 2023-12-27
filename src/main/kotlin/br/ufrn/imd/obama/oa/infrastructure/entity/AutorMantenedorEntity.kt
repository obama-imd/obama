package br.ufrn.imd.obama.oa.infrastructure.entity;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table


@Entity
@Table(name = "autor_mantenedor")
class AutorMantenedorEntity (
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_mantenedor_gen")
	@SequenceGenerator(name="autor_mantenedor_gen", sequenceName = "sq_autor_mantenedor_id", allocationSize = 1)
	val id: Long = 0L,

	@Column(name = "nome")
	val nome: String = "",

	@Column(name = "email")
	val email: String = "",

	@Column(name = "site")
	val site: String = ""
)
