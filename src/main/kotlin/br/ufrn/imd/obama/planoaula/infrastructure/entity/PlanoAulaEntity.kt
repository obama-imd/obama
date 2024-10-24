package br.ufrn.imd.obama.planoaula.infrastructure.entity

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoAula
import br.ufrn.imd.obama.usuario.infrastructure.entity.UsuarioEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="plano_aula")
data class PlanoAulaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_aula_gen")
    @SequenceGenerator(name = "plano_aula_gen", sequenceName = "sq_plano_aula_id", allocationSize = 1)
    private val id: Long,

    @Column(name = "data_cadastro", nullable = false)
    private val dataCadastro: LocalDateTime,

    @Column(name = "qtd_downloads", nullable = false)
    private val qtdDownload: Int = 0,

    @Column(name = "escola", nullable = true)
    private val escola: String?,

    @Column(name = "duracao_em_minutos", nullable = true)
    private var duracaoEmMinutos: Int?,

    @Column(name = "titulo", nullable = true)
    private var titulo: String?,

    @Column(columnDefinition = "text", nullable = true)
    private val resumo: String?,

    @Column(columnDefinition = "text", name = "objetivo_geral", nullable = true)
    private val objetivoGeral: String?,

    @Column(columnDefinition = "text", name = "objetivos_especificos", nullable = true)
    private val objetivosEspecificos: String?,

    @Column(columnDefinition = "text", nullable = true)
    private val metodologia: String?,

    @Column(columnDefinition = "text", nullable = true)
    private val referencias: String?,

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private val token: String?,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private val status: StatusPlanoAula,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private val autor: UsuarioEntity?,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private val nivelEnsino: NivelEnsinoEntity?,

    @Column(columnDefinition = "text", nullable = true)
    private val avaliacao: String?,

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_disciplina",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "disciplina_id", referencedColumnName = "id")]
    )
    private val disciplinasEnvolvidas: List<DisciplinaEntity>?,

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private var anoEnsino: AnoEnsinoEntity?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_objeto_aprendizagem",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")]
    )
    private val objetosAprendizagem: Set<ObjetoAprendizagemEntity>?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_coautor",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "usuario_id", referencedColumnName = "id")]
    )
    private val coautores: Set<UsuarioEntity>?,
) {
    fun getId() = id
    fun getDataCadastro() = dataCadastro
    fun getQtdDownload() = qtdDownload
    fun getEscola() = escola
    fun getDuracaoEmMinutos() = duracaoEmMinutos
    fun getTitulo() = titulo
    fun getResumo() = resumo
    fun getObjetivoGeral() = objetivoGeral
    fun getObjetivosEspecificos() = objetivosEspecificos
    fun getMetodologia() = metodologia
    fun getReferencias() = referencias
    fun getToken() = token
    fun getStatus() = status
    fun getAutor() = autor
    fun getNivelEnsino() = nivelEnsino
    fun getDisciplinasEnvolvidas() = disciplinasEnvolvidas
    fun getAnoEnsino() = anoEnsino
    fun getObjetosAprendizagem() = objetosAprendizagem
    fun getCoautores() = coautores

    fun getAvaliacao() = avaliacao

    fun setTitulo(titulo: String) {
        this.titulo = titulo
    }

    fun setIdanoEnsino(anoEnsino: AnoEnsinoEntity) {
        this.anoEnsino = anoEnsino
    }
}
