package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.infrastructure.repository.HabilidadeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [HabilidadeGatewayAdapter::class, HabilidadeRepository::class])
class HabilidadeGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: HabilidadeGatewayAdapter

    @MockBean
    private lateinit  var habilidadeRepository: HabilidadeRepository

}