package br.ufrn.imd.obama.planoaula.infrastructure.entity

import br.ufrn.imd.obama.oa.infrastructure.entity.AnoEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.NivelEnsinoEntity
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.planoaula.domain.enums.StatusPlanoDeAula
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
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="plano_aula")
data class PlanoAula(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Column(name = "data_cadastro", nullable = true)
    private val dataCadastro: LocalDateTime,

    @Column(name = "qtd_downloads", nullable = false)
    private val qtdDownload: Int = 0,

    @Column(name = "escola", nullable = true)
    private val escola: String?,

    @Column(name = "duracao_em_minutos", nullable = true)
    private var duracaoEmMinutos: Int? = null,

    @Column(name = "titulo", nullable = true)
    private val titulo: String? = null,

    @Column(columnDefinition = "text", nullable = true)
    private val resumo: String? = null,

    @Column(columnDefinition = "text", name = "objetivo_geral", nullable = true)
    private val objetivoGeral: String? = null,

    @Column(columnDefinition = "text", name = "objetivos_especificos", nullable = true)
    private val objetivosEspecificos: String? = null,

    @Column(columnDefinition = "text", nullable = true)
    private val metodologia: String? = null,

    @Column(columnDefinition = "text", nullable = true)
    private val avaliacao: String? = null,

    @Column(columnDefinition = "text", nullable = true)
    private val referencias: String? = null,

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private val token: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private val status: StatusPlanoDeAula,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private val autor: UsuarioEntity? = null,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private var nivelEnsino: NivelEnsinoEntity? = null,

    @OneToMany(fetch = FetchType.EAGER)
    private val disciplinasEnvolvidas: List<DisciplinaEntity>? = null,

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    private var anoEnsino: AnoEnsinoEntity? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_objeto_aprendizagem",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")]
    )
    private val objetosAprendizagem: Set<ObjetoAprendizagemEntity>? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_coautor",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "usuario_id", referencedColumnName = "id")]
    )
    private val coautores: Set<UsuarioEntity>? = null,
)
