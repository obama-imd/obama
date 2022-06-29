package br.ufrn.imd.obama.dominio

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
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
@Table(name="objetoaprendizagem", schema="public")
data class ObjetoAprendizagem (

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long = 0,

	@Column(name="nome")
	val nome: String = "",

	@Column(name="descricao", columnDefinition="text")
	var descricao: String = "",

	@Column(name="qtd_acessos")
	val quantidadeAcessos: Int = 0,

	@Column(name="link", columnDefinition="text")
	val link: String = "",

	@Column(name="path_arquivo")
	val pathArquivo: String = "",

	@Column(name="data_lancamento")
	val dataLancamento: LocalDate = LocalDate.now(),

	@JoinColumn(name="id_tipo_objeto")
	@ManyToOne
	var tipoObjeto: TipoObjeto = TipoObjeto(),

	@Column(name="versao")
	val versao: String = "",

	@Column(name="ativo")
	val ativo: Boolean = false,

	@Column(name="tipo_visualizacao")
	val tipoVisualizacao: Int = 0,

	@JoinColumn(name="id_plataforma")
	@ManyToOne
	val plataforma: Plataforma = Plataforma(),

	@JoinColumn(name="id_tipo_licenca_uso")
	@ManyToOne
	val licencaDeUso: TipoLicencaUso = TipoLicencaUso(),
) {
	@ManyToMany
	@JoinTable(name="objetoaprendizagem_idioma", schema="public")
	val idioma: List<Idioma> = emptyList()

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name="objetoaprendizagem_autormantenedor", schema="public")
	val autoresMantenedores: List<AutorMantenedor> =  emptyList()

	@OneToMany
	@JoinTable(name="objetoaprendizagem_comentario", schema="public")
	val comentarios: List<Comentario> = emptyList()

	@OneToMany
	@JoinTable(name="objetoaprendizagem_planodeaula", schema="public")
	val planosDeAula :  List<PlanoDeAula> = emptyList()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="objetoaprendizagem_descritor",
		schema="public",
		joinColumns = [JoinColumn(name="objetoaprendizagem", referencedColumnName="id")],
		inverseJoinColumns=[JoinColumn(name="descritor_id", referencedColumnName="id")]
	)
	val descritores: Set<Descritor> = hashSetOf()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="objetoaprendizagem_habilidade",
		schema="public",
		joinColumns = [JoinColumn(name="objetoaprendizagem", referencedColumnName="id")],
		inverseJoinColumns=[JoinColumn(name="habilidade_id", referencedColumnName="id")]
	)
	val habilidades: Set<Habilidade> = hashSetOf()

	@Transient
	val conteudosPorNivel: HashMap<NivelEnsino, Set<TemaConteudo>> = hashMapOf()

	@Transient
	val oaSelecionado: Boolean = false

	@Transient
	val oaFavorito: Boolean = false
}
