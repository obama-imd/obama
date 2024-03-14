package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.infrastructure.entity.DescritorEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.DescritorRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
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
}
