package br.ufrn.imd.obama.oa.infrastructure.adapter

import br.ufrn.imd.obama.oa.domain.model.ObjetoAprendizagem
import br.ufrn.imd.obama.oa.infrastructure.entity.ObjetoAprendizagemEntity
import br.ufrn.imd.obama.oa.infrastructure.mapper.toEntity
import br.ufrn.imd.obama.oa.infrastructure.repository.ObjetoAprendizagemRepository
import br.ufrn.imd.obama.oa.util.criarObjetoAprendizagem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.Optional

@ActiveProfiles(profiles = ["test"])
@SpringBootTest(classes = [
    ObjetoAprendizagemDatabaseGatewayAdapter::class,
    ObjetoAprendizagemRepository::class
])
class ObjetoAprendizagemDatabaseGatewayAdapterTest {

    @Autowired
    private lateinit var gatewayAdapter: ObjetoAprendizagemDatabaseGatewayAdapter

    @MockBean
    private lateinit var objetoAprendizagemRepository: ObjetoAprendizagemRepository

    @Test
    fun `Deve fazer busca no repository e encontrar uma OA`() {
        val resultado = criarObjetoAprendizagem()

        `when`(
            objetoAprendizagemRepository.findById(resultado.id)
        ).thenReturn(
            Optional.of(resultado.toEntity())
        )

        var oa: ObjetoAprendizagem? = null

        assertDoesNotThrow {
            oa = gatewayAdapter.procurarPorID(resultado.id)
        }

        Assertions.assertEquals(resultado, oa)
    }

    @Test
    fun `Deve fazer busca no repository e retornar NoSuchElementException`() {
        val idInexistente = 0L
        val resultadoNulo = Optional.empty<ObjetoAprendizagemEntity>()

        `when`(
            objetoAprendizagemRepository.findById(idInexistente)
        ).thenReturn(
            resultadoNulo
        )

        var oa: ObjetoAprendizagem? = null

        Assertions.assertThrows(NoSuchElementException::class.java) {
            oa = gatewayAdapter.procurarPorID(idInexistente)
        }
    }

}
