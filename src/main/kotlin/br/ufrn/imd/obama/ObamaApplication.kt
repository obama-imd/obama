package br.ufrn.imd.obama

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ObamaApplication

fun main(args: Array<String>) {
	runApplication<ObamaApplication>(*args)
}
