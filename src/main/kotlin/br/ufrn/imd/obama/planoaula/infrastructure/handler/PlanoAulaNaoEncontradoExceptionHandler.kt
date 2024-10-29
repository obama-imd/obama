package br.ufrn.imd.obama.planoaula.infrastructure.handler

import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaNaoEncontradoException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class PlanoAulaNaoEncontradoExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(PlanoAulaNaoEncontradoException::class)
    fun handlePlanoAulaNaoEncontradoException(ex: PlanoAulaNaoEncontradoException): ResponseEntity<Any> {
        logger.error(ex.message)

        return ResponseEntity.notFound().build()
    }

}
