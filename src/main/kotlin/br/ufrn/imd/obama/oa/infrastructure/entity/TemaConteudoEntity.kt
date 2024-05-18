package br.ufrn.imd.obama.oa.infrastructure.entity;

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode


@Entity
@Table(name="tema_conteudo")
class TemaConteudoEntity (

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tema_conteudo_gen")
	@SequenceGenerator(name="tema_conteudo_gen", sequenceName = "sq_tema_conteudo_id", allocationSize = 1)
	val id: Long,

	@Column(name = "nome")
	val nome: String,

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="disciplina_id")
	val disciplina: DisciplinaEntity,

	@Enumerated(EnumType.STRING)
	@Column(name = "curriculo")
	val curriculo: Curriculo,
){

	@OneToMany(mappedBy = "temaConteudo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, )
	@Fetch(value= FetchMode.SELECT)
	val habilidades: MutableSet<HabilidadeEntity> = hashSetOf()
}
