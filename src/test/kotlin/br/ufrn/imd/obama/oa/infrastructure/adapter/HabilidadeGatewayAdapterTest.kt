package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.HabilidadeV2
import br.ufrn.imd.obama.oa.infrastructure.entity.HabilidadeEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.HabilidadeRepository
import br.ufrn.imd.obama.oa.util.criarHabilidade
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [HabilidadeGatewayAdapter::class, HabilidadeRepository::class])
class HabilidadeGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: HabilidadeGatewayAdapter

    @MockBean
    private lateinit  var habilidadeRepository: HabilidadeRepository

}