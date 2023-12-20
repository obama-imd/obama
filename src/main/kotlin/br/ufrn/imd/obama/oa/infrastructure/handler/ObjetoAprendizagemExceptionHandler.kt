package br.ufrn.imd.obama.oa.infrastructure.handler

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ObjetoAprendizagemExceptionHandler: DefaultHandlerExceptionResolver() {
}
