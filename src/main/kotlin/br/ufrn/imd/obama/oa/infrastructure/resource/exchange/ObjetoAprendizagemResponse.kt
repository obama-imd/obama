package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import java.time.LocalDate

data class ObjetoAprendizagemResponse(
    val id: Long,
    val nome: String,
    val descricao: String,
    val quantidadeAcessos: Int,
    val thumbnailPath: String?,
    val dataLancamento: LocalDate?,
    val versao: String?,
    val ativo: Boolean,
    val tipoLicensaUsoId: Long?,
    val idiomaIds: Set<Long>?,
    val autorMantenedorIds: Set<Long>,
    val descritorIds: Set<Long>,
    val habilidadeIds: Set<Long>,
    val plataformaIds: List<Long>
)
