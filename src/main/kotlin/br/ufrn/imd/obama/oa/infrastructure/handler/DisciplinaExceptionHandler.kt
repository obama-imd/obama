package br.ufrn.imd.obama.oa.infrastructure.handler

import br.ufrn.imd.obama.oa.domain.exception.DisciplinaNaoEncontradoException
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class DisciplinaExceptionHandler: DefaultHandlerExceptionResolver() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(DisciplinaNaoEncontradoException::class)
    fun handleDisciplinaNaoEncontradaException(ex: DisciplinaNaoEncontradoException) : ResponseEntity<Any> {
        logger.error(ex)
        return ResponseEntity.badRequest().build()
    }
}
