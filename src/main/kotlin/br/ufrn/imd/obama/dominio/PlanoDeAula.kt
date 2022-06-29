package br.ufrn.imd.obama.dominio

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.MapKeyEnumerated
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name="planodeaula", schema="public")
data class PlanoDeAula (

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="data_cadastro")
	val dataCadastro: LocalDateTime = LocalDateTime.now(),

	@Column(name="qtd_downloads")
	val quantidadeDownloads: Int = 0,

	@Column(name="escola")
	val escola: String = "",

	@ManyToOne
	val autor: Usuario = Usuario(),

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	val nivelEnsino: NivelEnsino = NivelEnsino(),

	@Column(name="ano_ensino")
	@MapKeyEnumerated(EnumType.ORDINAL)
	val anoEnsino: Int = 1,

	@Column(name="duracao_em_minutos")
	val duracaoEmMinutos: Int = 0,

	@Column(name="titulo")
	val titulo: String = "",

	@Column(name = "resumo", columnDefinition="text")
	val resumo: String = "",

	@Column(columnDefinition="text", name="objetivo_geral")
	val objetivoGeral: String = "",

	@Column(columnDefinition="text", name="objetivos_especificos")
	val objetivosEspecificos: String = "",

	@Column(name = "metodologia", columnDefinition="text")
	val metodologia: String = "",

	@Column(name = "avaliacao", columnDefinition="text")
	val avaliacao: String = "",

	@Column(name = "referencias", columnDefinition="text")
	val referencias: String = "",

	@Column(name = "token", columnDefinition="text", unique = true, nullable = false)
	val token: String = "",

	@Column(name="status_id")
	@MapKeyEnumerated(EnumType.ORDINAL)
	val status: Int = 0
) {

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="planodeaula_disciplina", schema="public")
	val disciplinasEnvolvidas: List<Disciplina> = emptyList()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="planodeaula_objetoaprendizagem",
		schema="public",
		joinColumns = [JoinColumn(name="planodeaula_id", referencedColumnName="id")],
		inverseJoinColumns= [JoinColumn(name="objetosaprendizagem_id", referencedColumnName="id")]
	)
	val objetosAprendizagem: Set<ObjetoAprendizagem> = hashSetOf()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="planodeaula_coautor",
		schema="public",
		joinColumns = [JoinColumn(name="planodeaula_id", referencedColumnName="id")],
		inverseJoinColumns= [JoinColumn(name="usuario_id", referencedColumnName="id")]
	)
	val coautores: Set<Usuario> = hashSetOf()

	@OneToMany(
		mappedBy = "plano",
		fetch = FetchType.LAZY,
		cascade = [CascadeType.ALL]
	)
	val avaliacaoPlanoDeAula: List<AvaliacaoPlanoDeAula> = listOf()

	@OneToMany(fetch=FetchType.EAGER, cascade=[CascadeType.ALL])
	@JoinTable(name="planodeaula_comentario", schema="public")
	val comentarios: Set<Comentario> = hashSetOf()

	@Transient
	val comentariosFeedback: List<Comentario> = emptyList()

	@Transient
	val comentariosPublicos: List<Comentario> = emptyList()

	@Transient
	val planoEmRevisao: Boolean = false

	@Transient
	val planoEmRevisaoFinal: Boolean = false

	@Transient
	val revisorAtual: Usuario = Usuario()
}
