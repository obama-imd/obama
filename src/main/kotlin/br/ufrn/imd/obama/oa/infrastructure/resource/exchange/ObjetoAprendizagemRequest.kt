package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class ObjetoAprendizagemRequest(
    @field:NotBlank
    val nome: String,

    @field:NotBlank
    val descricao: String,

    @field:NotNull
    val quantidadeAcessos: Int = 0,

    @field:NotNull
    val ativo: Boolean = true,

    @field:NotEmpty
    val autorMantenedorIds: Set<Long>,

    @field:NotEmpty
    val descritorIds: Set<Long>,

    @field:NotEmpty
    val habilidadeIds: Set<Long>,

    @field:NotEmpty
    val plataformaIds: List<Long>,

    val thumbnailPath: String? = null,

    val dataLancamento: LocalDate? = null,

    val versao: String? = null,

    val tipoLicensaUsoId: Long = 0L,

    val idiomasIds: Set<Long> = emptySet()
)
