package br.ufrn.imd.obama.oa.domain.model.builder

import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor
import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.Habilidade
import br.ufrn.imd.obama.oa.domain.model.Idioma
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagemPlataforma
import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import java.time.LocalDate

class ObjetoAprendizagemBuilder(
    // Atributos obrigatórios
    private val id: Long,
    private val nome: String,
    private val descricao: String,
    private val quantidadeAcessos: Int,
    private val ativo: Boolean,
    private val autoresMantenedores: Set<AutorMantenedor>,
    private val descritores: Set<Descritor>,
    private val habilidades: Set<Habilidade>,
    private val plataformas: List<ObjetoAprendizagemPlataforma>

) {
    // Atributos opcionais
    private var thumbnailPath: String? = null
    private var dataLancamento: LocalDate? = null
    private var versao: String? = null
    private var tipoLicensaUso: TipoLicensaUso? = null
    private var idiomas: Set<Idioma>? = null

    fun thumbnailPath(thumbnailPath: String?) = apply { this.thumbnailPath = thumbnailPath }
    fun dataLancamento(dataLancamento: LocalDate?) = apply { this.dataLancamento = dataLancamento }
    fun versao(versao: String?) = apply { this.versao = versao }
    fun tipoLicensaUso(tipoLicensaUso: TipoLicensaUso?) = apply { this.tipoLicensaUso = tipoLicensaUso }
    fun idiomas(idiomas: Set<Idioma>?) = apply { this.idiomas = idiomas }

    fun build(): ObjetoAprendizagem {
        return ObjetoAprendizagem.create(
            id = id,
            nome = nome,
            descricao = descricao,
            quantidadeAcessos = quantidadeAcessos,
            thumbnailPath = thumbnailPath,
            dataLancamento = dataLancamento,
            versao = versao,
            ativo = ativo,
            tipoLicensaUso = tipoLicensaUso,
            idiomas = idiomas,
            autoresMantenedores = autoresMantenedores,
            descritores = descritores,
            habilidades = habilidades,
            plataformas = plataformas
        )
    }

}
