package br.ufrn.imd.obama

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages=["br.ufrn.imd.obama.repository"])
class ObamaApplication

fun main(args: Array<String>) {
	runApplication<ObamaApplication>(*args)
}
