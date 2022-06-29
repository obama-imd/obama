package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "AutorMantenedor", schema = "public")
data class AutorMantenedor (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="nome")
	val nome: String = "",

	@Column(name="email")
	val email: String = "",

	@Column(name="site")
	val site: String = ""
)
