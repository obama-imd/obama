package br.ufrn.imd.obama.planoaula.infrastructure.handler

import br.ufrn.imd.obama.planoaula.domain.exception.PlanoAulaDuracaoNegativaException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class PlanoAulaDuracaoNegativaExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(PlanoAulaDuracaoNegativaException::class)
    fun handlePlanoAulaDuracaoNegativaException(ex: PlanoAulaDuracaoNegativaException): ResponseEntity<Any> {
        logger.error(ex.message)

        return ResponseEntity.badRequest().build()
    }
}
