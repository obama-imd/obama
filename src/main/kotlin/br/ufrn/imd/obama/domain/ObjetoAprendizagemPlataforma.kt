package br.ufrn.imd.obama.domain

import br.ufrn.imd.oba.domain.ObjetoAprendizagemPlataformaPK
import br.ufrn.imd.obama.enums.TipoVisualizacao
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table


@Entity
@Table(name = "objetoaprendizagem_plataforma")
data class ObjetoAprendizagemPlataforma (
    @EmbeddedId
    val id: ObjetoAprendizagemPlataformaPK = ObjetoAprendizagemPlataformaPK(),

    @Column(name="access_type", nullable = false)
    @Enumerated(EnumType.STRING)
    val tipoVisualizacao: TipoVisualizacao = TipoVisualizacao.WEB,

    @Column(name="link", columnDefinition="text", nullable = false)
    val link: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("objetoAprendizagemId")
    val objetoAprendizagem: ObjetoAprendizagem = ObjetoAprendizagem(),

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("plataformaId")
    val plataforma: Plataforma = Plataforma()
)
