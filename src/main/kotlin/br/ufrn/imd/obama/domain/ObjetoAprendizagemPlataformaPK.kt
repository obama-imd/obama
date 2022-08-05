package br.ufrn.imd.oba.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ObjetoAprendizagemPlataformaPK(
    @Column(name="objeto_aprendizagem_id", nullable = false)
    val learningObjectId: Long = 0L,

    @Column(name="plataforma_id", nullable = false)
    val plataformId: Long = 0L,
): Serializable
