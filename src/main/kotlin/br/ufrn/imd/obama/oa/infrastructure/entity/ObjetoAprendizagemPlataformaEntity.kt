package br.ufrn.imd.obama.oa.infrastructure.entity

import br.ufrn.imd.obama.oa.domain.model.TipoAcesso
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table


@Entity
@Table(name = "objeto_aprendizagem_plataforma")
class ObjetoAprendizagemPlataformaEntity (
    @EmbeddedId
    val id: ObjetoAprendizagemPlataformaPK,

    @Column(name="tipo_acesso", nullable = false)
    @Enumerated(EnumType.STRING)
    val tipoAcesso: TipoAcesso,

    @Column(name="link", columnDefinition="text", nullable = false)
    val link: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("objetoAprendizagemId")
    val objetoAprendizagem: ObjetoAprendizagemEntity,

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("plataformaId")
    val plataforma: PlataformaEntity
)
