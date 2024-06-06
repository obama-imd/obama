package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.Disciplina
import br.ufrn.imd.obama.oa.infrastructure.entity.DisciplinaEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.DisciplinaRepository
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
    DisciplinaDatabaseGatewayAdapter::class,
    DisciplinaRepository::class
])
class DisciplinaDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var disciplinaGatewayAdapter: DisciplinaDatabaseGatewayAdapter

    @MockBean
    private lateinit var disciplinaRepository: DisciplinaRepository

    @Test
    fun `Deve fazer busca e retornar alguma dado`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val resultado: Page<DisciplinaEntity> = PageImpl(
            listOf(
                criarDisciplina().toEntity(),
                criarDisciplina().toEntity()
            )
        )

        `when`(
            disciplinaRepository.findAllByOrderByNomeAsc(
                pageable
            )
        ).thenReturn(
            resultado
        )

        var disciplinas: Page<Disciplina>? = null

        assertDoesNotThrow {
            disciplinas = disciplinaGatewayAdapter.listarDisciplinas(pageable)
        }

        Assertions.assertEquals(
            disciplinas?.isEmpty,
            false
        )

    }

    @Test
    fun `Deve fazer busca e retornar lista vazia`() {

        val pageable: Pageable = Pageable.ofSize(10)
        val resultado: Page<DisciplinaEntity> = Page.empty()

        `when`(
            disciplinaRepository.findAllByOrderByNomeAsc(
                pageable
            )
        ).thenReturn(
            resultado
        )

        Assertions.assertEquals(
            disciplinaGatewayAdapter.listarDisciplinas(pageable),
            Page.empty<Disciplina>()
        )

    }
}
