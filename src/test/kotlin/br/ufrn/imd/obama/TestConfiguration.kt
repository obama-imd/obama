package br.ufrn.imd.obama

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@TestConfiguration
@EnableJpaRepositories(basePackages = [
    "br.ufrn.imd.obama.oa.infrastructure.repository",
    "br.ufrn.imd.obama.usuario.infrastructure.repository",
    "br.ufrn.imd.obama.planoaula.infrastructure.repository"
])
class TestConfiguration {
}
