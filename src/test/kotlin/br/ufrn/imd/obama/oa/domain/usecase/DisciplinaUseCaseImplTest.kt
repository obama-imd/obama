package br.ufrn.imd.obama.oa.domain.usecase

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.adapter.DisciplinaDatabaseGatewayAdapter
import br.ufrn.imd.obama.oa.util.criarDisciplina
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [
    DisciplinaUseCaseImpl::class,
    DisciplinaDatabaseGatewayAdapter::class
])
class DisciplinaUseCaseImplTest {

    @Autowired
    private lateinit var disciplinaUseCase: DisciplinaUseCaseImpl

    @MockBean
    private lateinit var disciplinaGatewayAdapter: DisciplinaDatabaseGatewayAdapter

    @Test
    fun `Deve fazer busca e retornar alguma dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val resultado: Page<Disciplina> = PageImpl(
            listOf(
                criarDisciplina(),
                criarDisciplina()
            )
        )

        `when`(
            disciplinaGatewayAdapter.listarDisciplinas(
                pageable
            )
        ).thenReturn(
            resultado
        )

        var disciplinas: Page<Disciplina>? = null

        assertDoesNotThrow {
            disciplinas = disciplinaUseCase.listarDisciplinas(pageable)
        }

        Assertions.assertEquals(
            disciplinas?.isEmpty,
            false
        )

    }

    @Test
    fun `Deve fazer busca e retornar lista vazia`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val resultado: Page<Disciplina> = Page.empty()

        `when`(
            disciplinaGatewayAdapter.listarDisciplinas(
                pageable
            )
        ).thenReturn(
            resultado
        )

        Assertions.assertEquals(
            disciplinaUseCase.listarDisciplinas(pageable),
            Page.empty<Disciplina>()
        )

    }
}
