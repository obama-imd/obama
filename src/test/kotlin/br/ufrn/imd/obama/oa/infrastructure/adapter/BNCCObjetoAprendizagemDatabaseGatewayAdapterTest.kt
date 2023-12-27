package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [BNCCObjetoAprendizagemDatabaseGatewayAdapter::class, ObjetoAprendizagemRepository::class])
class BNCCObjetoAprendizagemDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: BNCCObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository`() {
        val nome: String = "Mat"

        val pageable: Pageable = Pageable.ofSize(10)

        var resultado: Page<ObjetoAprendizagemEntity> = Page.empty()

        `when`(
            objetoAprendizagemRepository.findAllAtivoByNomeAndTipoAcessoAndNivelEnsinoIdAndTemaConteudoIdAndDescritorId(
                nome,
                null,
                null,
                null,
                null,
                pageable
            )
        ).thenReturn(
            resultado
        )
    }
}
