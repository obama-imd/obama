package br.ufrn.imd.obama.oa.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate


@Entity
@Table(name="objeto_aprendizagem")
class ObjetoAprendizagemEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objeto_aprendizagem_gen")
	@SequenceGenerator(name="objeto_aprendizagem_gen", sequenceName = "sq_objeto_aprendizagem_id", allocationSize = 1)
	val id: Long,

	@Column(name="nome")
	var nome: String,

	@Column(name="descricao", columnDefinition="text")
	var descricao: String,

	@Column(name="quantidade_acessos")
	var quantidadeAcessos: Int = 0,

	@Column(name="caminho_thumbnail")
	var thumbnailPath: String?,

	@Column(name="data_lancamento", nullable = true)
	val dataLancamento: LocalDate?,

	@Column(name="versao", nullable = true)
	var versao: String?,

	@Column(name="ativo")
	var ativo: Boolean,

	@JoinColumn(name="tipo_licensa_uso_id")
	@ManyToOne
	var tipoLicensaUso: TipoLicensaUsoEntity?
) {
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
		name="objeto_aprendizagem_idioma",
		joinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")],
		inverseJoinColumns=[JoinColumn(name="idioma_id", referencedColumnName="id")]
	)
	val idiomas: MutableSet<IdiomaEntity>? = hashSetOf()

	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
		name="objeto_aprendizagem_autor_mantenedor",
		joinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")],
		inverseJoinColumns=[JoinColumn(name="autor_mantenedor_id", referencedColumnName="id")]
	)
	val autoresMantenedores: MutableSet<AutorMantenedorEntity> = hashSetOf()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="objeto_aprendizagem_descritor",
		joinColumns = [JoinColumn(name="objeto_aprendizagem_id", referencedColumnName="id")],
		inverseJoinColumns=[JoinColumn(name="descritor_id", referencedColumnName="id")]
	)
	val descritores: MutableSet<DescritorEntity> = hashSetOf()

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="objeto_aprendizagem_habilidade",
		joinColumns = [JoinColumn(name="objeto_aprendizagem_id", referencedColumnName="id")],
		inverseJoinColumns=[JoinColumn(name="habilidade_id", referencedColumnName="id")]
	)
	val habilidades: MutableSet<HabilidadeEntity> = hashSetOf()

	@OneToMany(mappedBy = "objetoAprendizagem", fetch = FetchType.EAGER)
	val objetoAprendizagemPlataformas: MutableList<ObjetoAprendizagemPlataformaEntity> = mutableListOf()
}
