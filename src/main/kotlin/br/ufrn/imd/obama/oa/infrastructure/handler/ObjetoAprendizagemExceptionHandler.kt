package br.ufrn.imd.obama.oa.infrastructure.handler

import br.ufrn.imd.obama.oa.domain.exception.AutorMantenedorNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.DescritorNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.HabilidadeNaoEncontradaException
import br.ufrn.imd.obama.oa.domain.exception.IdiomaNaoEncontradoException
import br.ufrn.imd.obama.oa.domain.exception.ObjetoAprendizagemPlataformaNaoEncontradaException
import br.ufrn.imd.obama.oa.domain.exception.TipoLicensaUsoNaoEncontradoException
import br.ufrn.imd.obama.oa.infrastructure.exception.OANaoEncontradoException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import br.ufrn.imd.obama.oa.infrastructure.resource.exchange.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ObjetoAprendizagemExceptionHandler: DefaultHandlerExceptionResolver() {
    private val logger = LoggerFactory.getLogger(javaClass)

    //TODO: logger estava dando erro, corrigir!

    @ExceptionHandler(TipoLicensaUsoNaoEncontradoException::class)
    fun handleTipoLicensaUsoNaoEncontradoException(ex: TipoLicensaUsoNaoEncontradoException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "TipoLicensaUsoNaoEncontrado",
            message = ex.message ?: "Tipo de licença de uso não encontrado"
        )

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(IdiomaNaoEncontradoException::class)
    fun handleIdiomaNaoEncontradoException(ex: IdiomaNaoEncontradoException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "IdiomaNaoEncontrado",
            message = ex.message ?: "Idioma não encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(AutorMantenedorNaoEncontradoException::class)
    fun handleAutorMantenedorNaoEncontradoException(ex: AutorMantenedorNaoEncontradoException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "AutorMantenedorNaoEncontrado",
            message = ex.message ?: "Autor/Mantenedor não encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(DescritorNaoEncontradoException::class)
    fun handleDescritorNaoEncontradoException(ex: DescritorNaoEncontradoException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "DescritorNaoEncontrado",
            message = ex.message ?: "Descritor não encontrado"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(HabilidadeNaoEncontradaException::class)
    fun handleHabilidadeNaoEncontradaException(ex: HabilidadeNaoEncontradaException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "HabilidadeNaoEncontrada",
            message = ex.message ?: "Habilidade não encontrada"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(ObjetoAprendizagemPlataformaNaoEncontradaException::class)
    fun handleObjetoAprendizagemPlataformaNaoEncontradaException(ex: ObjetoAprendizagemPlataformaNaoEncontradaException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "ObjetoAprendizagemPlataformaNaoEncontrada",
            message = ex.message ?: "Plataforma não encontrada"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
//        logger.error("Erro ao processar requisição: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "BadRequest",
            message = ex.message ?: "Requisição inválida"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
//        logger.error("Erro interno no servidor: {}", ex.message, ex)
        logger.error(ex.message)
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "InternalServerError",
            message = "Erro interno no servidor"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errorResponse)
    }

}
