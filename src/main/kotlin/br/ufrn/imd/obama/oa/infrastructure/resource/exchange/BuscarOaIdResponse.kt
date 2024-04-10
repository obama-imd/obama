package br.ufrn.imd.obama.oa.infrastructure.resource.exchange

import br.ufrn.imd.obama.oa.domain.model.*
import java.time.LocalDate

class BuscarOaIdResponse (
    val nome: String,

    val descricao: String,

    val dataLancamento: LocalDate?,

    val autoresMantenedores: List<AutorMantenedor>,

    val descritores: List<Descritor>,

    val habilidades: List<Habilidade>,

    val acessos: List<Plataforma>
)