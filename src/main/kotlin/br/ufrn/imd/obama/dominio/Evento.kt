package br.ufrn.imd.obama.dominio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name="evento", schema="public")
data class Evento (
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long,

	@Column(name="nome")
	val nome: String,

	@Column(nullable=true)
	val ativo: Boolean,
	
	@Column(nullable=false)
	val deletado: Boolean,

	@ManyToOne
	val coordenador: Usuario
){
	@ManyToMany
	@JoinTable(
		schema="public",
		name="evento_usuario",
		joinColumns = [JoinColumn(name="evento", referencedColumnName="id")],
		inverseJoinColumns=[JoinColumn(name="usuario_id", referencedColumnName="id")]
	)
	val colaboradores: HashSet<Usuario> = hashSetOf()

	@OneToMany(mappedBy="evento")
	val atividades: List<Atividade> = listOf()
}
