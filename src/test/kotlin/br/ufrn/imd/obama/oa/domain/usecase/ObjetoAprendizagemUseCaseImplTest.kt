package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.enums.Curriculo
import br.ufrn.imd.obama.oa.domain.exception.*
import br.ufrn.imd.obama.oa.domain.gateway.*
import br.ufrn.imd.obama.oa.domain.model.AutorMantenedor
import br.ufrn.imd.obama.oa.domain.model.Idioma
import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.domain.model.TipoLicensaUso
import br.ufrn.imd.obama.oa.domain.model.builder.ObjetoAprendizagemBuilder
import br.ufrn.imd.obama.oa.infrastructure.adapter.ObjetoAprendizagemDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import br.ufrn.imd.obama.oa.util.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(
    classes = [
        ObjetoAprendizagemUseCaseImpl::class
    ]
)
class ObjetoAprendizagemUseCaseImplTest {

    @Autowired
    private lateinit var objetoAprendizagemGateway: ObjetoAprendizagemGateway

    @Autowired
    private lateinit var objetoAprendizagemUseCase: ObjetoAprendizagemUseCaseImpl

    @MockBean
    private lateinit var objetoAprendizagemDatabaseGatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var autorMantenedorGateway: AutorMantenedorGateway

    @MockBean
    private lateinit var descritorGateway: DescritorDatabaseGateway

    @MockBean
    private lateinit var habilidadeGateway: HabilidadeGateway

    @MockBean
    private lateinit var plataformaGateway: ObjetoAprendizagemPlataformaGateway

    @MockBean
    private lateinit var tipoLicensaUsoGateway: TipoLicensaUsoGateway

    @MockBean
    private lateinit var idiomaGateway: IdiomaGateway

    @Test
    fun `Deve achar Objeto de Aprendizagem`() {
        val resultado = criarObjetoAprendizagem()

        `when`(
            objetoAprendizagemDatabaseGatewayAdapter.procurarPorID(resultado.id)
        ).thenReturn(
            resultado
        )

        var oa: ObjetoAprendizagem?= null

        assertDoesNotThrow {
            oa = objetoAprendizagemUseCase.buscarPorId(resultado.id)
        }

        Assertions.assertEquals(resultado, oa)
    }

    @Test
    fun `Deve retornar OANaoEncontradoException`() {
        val idInexistente = 0L

        `when`(
            objetoAprendizagemDatabaseGatewayAdapter.procurarPorID(idInexistente)
        ).thenThrow(OANaoEncontradoException::class.java)

        Assertions.assertThrows(OANaoEncontradoException::class.java) {
            objetoAprendizagemUseCase.buscarPorId(idInexistente)
        }
    }

    @Test
    fun `Deve achar algum objeto de aprendizagem`() {
        val curriculo = Curriculo.BNCC.name
        val nome= "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = PageImpl(
            listOf(
                criarObjetoAprendizagem(),
                criarObjetoAprendizagem()
            ),
        )

        `when`(
            objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
                habilidadeId = null
            )
        ).thenReturn(resultado);

        var paginas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            paginas = objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
                habilidadeId = null
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, false)
    }

    @Test
    fun `Deve achar nenhum objeto de aprendizagem`() {
        val nome= "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagem> = Page.empty(pageable)

        `when`(
            objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
                habilidadeId = null
            )
        ).thenReturn(resultado);

        var paginas: Page<ObjetoAprendizagem>? = null

        assertDoesNotThrow {
            paginas = objetoAprendizagemUseCase.buscarPorParametros(
                pageable,
                nome,
                null,
                null,
                null,
                null,
                null,
                habilidadeId = null
            )
        }

        Assertions.assertEquals(paginas?.isEmpty, true)
    }

    @Test
    fun `deve cadastrar objeto aprendizagem com sucesso`() {
        // Dados de entrada
        val tipoLicensaUso = TipoLicensaUso(id = 1L, nome = "CC BY", versao = "1")
        val idioma = Idioma(id = 1L, nome = "Português")
        val autor = criarAutorMantenedor()
        val descritor = criarDescritor()
        val habilidade = criarHabilidade()
        val plataforma = criarOAPlataforma()
        val dataLancamento = LocalDate.now()

        // Objeto esperado como entrada e retorno
        val objetoAprendizagemInput = ObjetoAprendizagemBuilder(
            id = 0L, // ID inicial, pois o gateway atribuirá o ID final
            nome = "OA Teste",
            descricao = "Descrição teste",
            quantidadeAcessos = 100,
            ativo = true,
            autoresMantenedores = setOf(autor),
            descritores = setOf(descritor),
            habilidades = setOf(habilidade),
            plataformas = listOf(plataforma)
        )
            .thumbnailPath("/path")
            .dataLancamento(dataLancamento)
            .versao("1.0")
            .tipoLicensaUso(tipoLicensaUso)
            .idiomas(setOf(idioma))
            .build()

        val objetoAprendizagemRetornado = ObjetoAprendizagemBuilder(
            id = 1L, // ID atribuído pelo gateway
            nome = "OA Teste",
            descricao = "Descrição teste",
            quantidadeAcessos = 100,
            ativo = true,
            autoresMantenedores = setOf(autor),
            descritores = setOf(descritor),
            habilidades = setOf(habilidade),
            plataformas = listOf(plataforma)
        )
            .thumbnailPath("/path")
            .dataLancamento(dataLancamento)
            .versao("1.0")
            .tipoLicensaUso(tipoLicensaUso)
            .idiomas(setOf(idioma))
            .build()

        // Configuração dos mocks
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(1L)).thenReturn(autor)
        `when`(descritorGateway.buscarPorId(1L)).thenReturn(descritor)
        `when`(habilidadeGateway.buscarPorId(1L)).thenReturn(habilidade)
        `when`(plataformaGateway.buscarPorId(1L)).thenReturn(plataforma)
        `when`(objetoAprendizagemGateway.cadastrarObjetoAprendizagem(objetoAprendizagemInput))
            .thenReturn(objetoAprendizagemRetornado)

        // Execução do método
        val result = assertDoesNotThrow {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = dataLancamento,
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }

        // Validações
        Assertions.assertEquals("OA Teste", result.nome)
        Assertions.assertEquals("Descrição teste", result.descricao)
        Assertions.assertEquals(100, result.quantidadeAcessos)
        Assertions.assertEquals("/path", result.thumbnailPath)
        Assertions.assertEquals(dataLancamento, result.dataLancamento)
        Assertions.assertEquals("1.0", result.versao)
        Assertions.assertEquals(true, result.ativo)
        Assertions.assertEquals(tipoLicensaUso, result.tipoLicensaUso)
        Assertions.assertEquals(setOf(idioma), result.idiomas)
        Assertions.assertEquals(setOf(autor), result.autoresMantenedores)
        Assertions.assertEquals(setOf(descritor), result.descritores)
        Assertions.assertEquals(setOf(habilidade), result.habilidades)
        Assertions.assertEquals(listOf(plataforma), result.plataformas)
    }

    @Test
    fun `deve lancar TipoLicensaUsoNaoEncontradoException ao cadastrar com tipo licensa uso inexistente`() {
        `when`(tipoLicensaUsoGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<TipoLicensaUsoNaoEncontradoException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 999L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }
    }

    @Test
    fun `deve lancar IdiomaNaoEncontradoException ao cadastrar com idioma inexistente`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "1"
        )
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<IdiomaNaoEncontradoException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(999L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }
    }

    @Test
    fun `deve lancar AutorMantenedorNaoEncontradoException ao cadastrar com autor inexistente`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "1"
        )
        val idioma = Idioma(id = 1L, nome = "Português")
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<AutorMantenedorNaoEncontradoException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(999L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }
    }

    @Test
    fun `deve lancar DescritorNaoEncontradoException ao cadastrar com descritor inexistente`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "1"
        )
        val idioma = Idioma(id = 1L, nome = "Português")
        val autor = AutorMantenedor(
            id = 1L, nome = "Autor Teste",
            email = "teste",
            site = "test"
        )
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(1L)).thenReturn(autor)
        `when`(descritorGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<DescritorNaoEncontradoException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(999L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }
    }

    @Test
    fun `deve lancar HabilidadeNaoEncontradaException ao cadastrar com habilidade inexistente`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "1"
        )
        val idioma = Idioma(id = 1L, nome = "Português")
        val autor = AutorMantenedor(
            id = 1L, nome = "Autor Teste",
            email = "TODO()",
            site =" TODO()"
        )
        val descritor = criarDescritor()
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(1L)).thenReturn(autor)
        `when`(descritorGateway.buscarPorId(1L)).thenReturn(descritor)
        `when`(habilidadeGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<HabilidadeNaoEncontradaException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(999L),
                plataformas = listOf(1L)
            )
        }
    }

    @Test
    fun `deve lancar ObjetoAprendizagemPlataformaNaoEncontradaException ao cadastrar com plataforma inexistente`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "TODO()"
        )
        val idioma = Idioma(id = 1L, nome = "Português")
        val autor = AutorMantenedor(
            id = 1L, nome = "Autor Teste",
            email = "TODO()",
            site = "TODO()"
        )
        val descritor = criarDescritor()
        val habilidade = criarHabilidade()
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(1L)).thenReturn(autor)
        `when`(descritorGateway.buscarPorId(1L)).thenReturn(descritor)
        `when`(habilidadeGateway.buscarPorId(1L)).thenReturn(habilidade)
        `when`(plataformaGateway.buscarPorId(999L)).thenReturn(null)

        assertThrows<ObjetoAprendizagemPlataformaNaoEncontradaException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = 100,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(999L)
            )
        }
    }

    @Test
    fun `deve lancar IllegalArgumentException ao cadastrar com quantidade acessos negativa`() {
        val tipoLicensaUso = TipoLicensaUso(
            id = 1L, nome = "CC BY",
            versao = "TODO()"
        )
        val idioma = Idioma(id = 1L, nome = "Português")
        val autor = criarAutorMantenedor()
        val descritor = criarDescritor() 
        val habilidade = criarHabilidade()
        val plataforma =  criarOAPlataforma()
        `when`(tipoLicensaUsoGateway.buscarPorId(1L)).thenReturn(tipoLicensaUso)
        `when`(idiomaGateway.buscarPorId(1L)).thenReturn(idioma)
        `when`(autorMantenedorGateway.buscarPorId(1L)).thenReturn(autor)
        `when`(descritorGateway.buscarPorId(1L)).thenReturn(descritor)
        `when`(habilidadeGateway.buscarPorId(1L)).thenReturn(habilidade)
        `when`(plataformaGateway.buscarPorId(1L)).thenReturn(plataforma)

        assertThrows<IllegalArgumentException> {
            objetoAprendizagemUseCase.cadastrarObjetoAprendizagem(
                nome = "OA Teste",
                descricao = "Descrição teste",
                quantidadeAcessos = -1,
                thumbnailPath = "/path",
                dataLancamento = LocalDate.now(),
                versao = "1.0",
                ativo = true,
                tipoLicensaUsoId = 1L,
                idiomaIds = setOf(1L),
                autorMantenedorIds = setOf(1L),
                descritorIds = setOf(1L),
                habilidadeIds = setOf(1L),
                plataformas = listOf(1L)
            )
        }
    }
}
