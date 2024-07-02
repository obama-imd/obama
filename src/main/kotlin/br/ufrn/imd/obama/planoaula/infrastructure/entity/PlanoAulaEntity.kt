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
    val id: Long,

    @Column(name = "data_cadastro", nullable = false)
    val dataCadastro: LocalDateTime,

    @Column(name = "qtd_downloads", nullable = false)
    val qtdDownload: Int = 0,

    @Column(name = "escola", nullable = true)
    val escola: String?,

    @Column(name = "duracao_em_minutos", nullable = true)
    var duracaoEmMinutos: Int?,

    @Column(name = "titulo", nullable = true)
    val titulo: String?,

    @Column(columnDefinition = "text", nullable = true)
    val resumo: String?,

    @Column(columnDefinition = "text", name = "objetivo_geral", nullable = true)
    val objetivoGeral: String?,

    @Column(columnDefinition = "text", name = "objetivos_especificos", nullable = true)
    val objetivosEspecificos: String?,

    @Column(columnDefinition = "text", nullable = true)
    val metodologia: String?,

    @Column(columnDefinition = "text", nullable = true)
    val referencias: String?,

    @Column(columnDefinition = "text", unique = true, nullable = false)
    val token: String?,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: StatusPlanoAula,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    val autor: UsuarioEntity?,

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    val nivelEnsino: NivelEnsinoEntity?,

    @OneToMany(fetch = FetchType.EAGER)
    val disciplinasEnvolvidas: List<DisciplinaEntity>?,

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    val anoEnsino: AnoEnsinoEntity?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_objeto_aprendizagem",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "objeto_aprendizagem_id", referencedColumnName = "id")]
    )
    val objetosAprendizagem: Set<ObjetoAprendizagemEntity>?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "plano_aula_coautor",
        joinColumns = [JoinColumn(name = "plano_aula_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "usuario_id", referencedColumnName = "id")]
    )
    val coautores: Set<UsuarioEntity>?,
)
