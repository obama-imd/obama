package br.ufrn.imd.obama.usuario.infrastructure.handler

import br.ufrn.imd.obama.usuario.domain.exception.TokenInvalidaException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioExistenteException
import br.ufrn.imd.obama.usuario.domain.exception.UsuarioNaoEncontradoException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class UsuarioExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler
    protected fun handleUsuarioNaoEncontradoException(
        ex: UsuarioNaoEncontradoException
    ): ResponseEntity<Any?>? {

        return ResponseEntity.badRequest().build()
    }

    @ExceptionHandler(UsuarioExistenteException::class)
    protected fun handleUsuarioExistenteException(
        ex: UsuarioExistenteException
    ):ResponseEntity<Any> {
        return ResponseEntity.badRequest().build()
    }

    @ExceptionHandler(TokenInvalidaException::class)
    protected fun handleUsuarioExistenteException(
        ex: TokenInvalidaException
    ):ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

}
