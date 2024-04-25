package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.Descritor
import br.ufrn.imd.obama.oa.domain.model.NivelEnsino
import br.ufrn.imd.obama.oa.infrastructure.entity.DescritorEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.DescritorRepository
import br.ufrn.imd.obama.oa.util.criarDescritor
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
@SpringBootTest(classes = [DescritorDatabaseGatewayAdapter::class, DescritorRepository::class])
class DescritorDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: DescritorDatabaseGatewayAdapter

    @MockBean
    private lateinit var descritorRepository: DescritorRepository

    @Test
    fun `Deve fazer busca no repository nenhum dado`() {

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<DescritorEntity> = Page.empty()

        `when`(
            descritorRepository.findAllByOrderByCodigoAsc(
                pageable
            )
        ).thenReturn(
            resultado
        )

        assertDoesNotThrow {
            gatewayAdapter.listarDescritores(
                pageable,
            )
        }
    }

    @Test
    fun `Deve fazer busca no repository e achar algum dado`() {

        val resultado: Page<DescritorEntity> = PageImpl(
            listOf(
                criarDescritor().toEntity(),
                criarDescritor().toEntity()
            ),
        )

        val pageable: Pageable = Pageable.ofSize(10)

        `when`(
            descritorRepository.findAllByOrderByCodigoAsc(pageable)
        ).thenReturn(
            resultado
        )

        var descritores: Page<Descritor>? = null

        assertDoesNotThrow {
            descritores = gatewayAdapter.listarDescritores(pageable)
        }

        Assertions.assertEquals(descritores?.isEmpty, false)
    }
}
