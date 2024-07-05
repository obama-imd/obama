package br.ufrn.imd.obama

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.ufrn.imd.obama"])
class ObamaApplication

fun main(args: Array<String>) {
	runApplication<ObamaApplication>(*args)
}
