package br.ufrn.imd.obama.oa.infrastructure.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable


@Embeddable
class ObjetoAprendizagemPlataformaPK(
   @Column(name="objeto_aprendizagem_id", nullable = false)
   val objetoAprendizagemId: Long,

   @Column(name="plataforma_id", nullable = false)
   val plataformaId: Long,
): Serializable
