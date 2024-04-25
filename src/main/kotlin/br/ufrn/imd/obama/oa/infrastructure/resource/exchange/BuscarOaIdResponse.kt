package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import br.ufrn.imd.obama.oa.domain.model.*
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class BuscarOaIdResponse (
    val nome: String,

    val descricao: String,

    @JsonProperty("data_lancamento")
    val dataLancamento: LocalDate?,

    @JsonProperty("autores_mantenedores")
    val autoresMantenedores: List<AutorMantenedor>,

    val descritores: List<Descritor>,

    val habilidades: List<Habilidade>,

    val acessos: List<Plataforma>
)
