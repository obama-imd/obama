package br.ufrn.imd.obama.dominio

import br.ufrn.imd.obama.enums.Curriculo
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="temaconteudo", schema="public")
data class TemaConteudo (
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	val id: Long,

	@Column(name = "denominacao")
	val denominacao: String,

	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="disciplina")
	val disciplina: Disciplina,

	@Enumerated(EnumType.STRING)
	val curriculo: Curriculo,
) {
	@Transient
	val descritores: List<Descritor> = listOf()
}
