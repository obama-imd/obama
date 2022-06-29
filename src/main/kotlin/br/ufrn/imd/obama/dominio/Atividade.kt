
package br.ufrn.imd.obama.dominio

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "atividade", schema = "public")
data class Atividade(
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="nome")
	val nome: String = "",

	@Column(name="data_inicial")
	val dataInicial: String = "",

	@Column(name="data_final")
	val dataFinal: String = "",

	@Column(name="carga_horaria")
	val cargaHoraria: Int = 0,

	@Column(name="local")
	val local: String = "",

	@Column(name="estado")
	val estado: String = "",

	@Column(name="ativo")
	val ativo: Boolean = false,

	@ManyToOne
	val evento: Evento = Evento()
) {

	@OneToMany(mappedBy="atividade", cascade =[CascadeType.ALL] ,fetch = FetchType.EAGER)
	val participantes: Set<Participante> = hashSetOf()
}
