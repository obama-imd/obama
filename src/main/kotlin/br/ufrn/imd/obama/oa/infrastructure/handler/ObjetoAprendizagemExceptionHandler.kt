package br.ufrn.imd.obama.oa.infrastructure.handler

import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ObjetoAprendizagemExceptionHandler: DefaultHandlerExceptionResolver() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(NoSuchBeanDefinitionException::class)
    fun handlerNoSuchBeanDefinitionException(ex: NoSuchBeanDefinitionException) : ResponseEntity<Any> {
        logger.error(ex)
        return ResponseEntity.badRequest().build()
    }

    @ExceptionHandler(OANaoEncontradoException::class)
    fun handleOANaoEncontradaException(ex: OANaoEncontradoException) : ResponseEntity<Any> {
        logger.error(ex)
        return ResponseEntity.notFound().build()
    }

}
