package com.prongbang.apispeccodegen.exceptions

import com.prongbang.apispeccodegen.apis.login.controller.LoginController
import com.prongbang.apispeccodegen.apis.person.controller.PersonController
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ControllerAdvice(
    assignableTypes = [
        PersonController::class,
        LoginController::class,
    ]
)
class GlobalControllerAdvice {

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    fun problem(e: Throwable): ResponseEntity<Problem> {
        val message = e.message
        val uuid = UUID.randomUUID()
        val logRef = uuid.toString()
        logger.error("logRef=$logRef", message, e)
        return ResponseEntity(Problem(logRef, message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<Any?> {
        val fieldErrors = ex.bindingResult.fieldErrors
        val globalErrors = ex.bindingResult.globalErrors
        val errors: MutableList<String> = ArrayList(fieldErrors.size + globalErrors.size)
        var error: String
        for (fieldError in fieldErrors) {
            error = fieldError.field + ", " + fieldError.defaultMessage
            errors.add(error)
        }
        for (objectError in globalErrors) {
            error = objectError.objectName + ", " + objectError.defaultMessage
            errors.add(error)
        }
        val errorMessage = ErrorMessage(errors)

        return ResponseEntity<Any?>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun handleConstraintViolatedException(
        ex: ConstraintViolationException
    ): ResponseEntity<Any?> {
        val constraintViolations = ex.constraintViolations
        val errors: MutableList<String> = ArrayList(constraintViolations.size)
        var error: String
        for (constraintViolation in constraintViolations) {
            error = constraintViolation.message
            errors.add(error)
        }
        val errorMessage = ErrorMessage(errors)
        return ResponseEntity<Any?>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun handleMissingServletRequestParameterException(
        ex: MissingServletRequestParameterException
    ): ResponseEntity<Any?> {
        val errors: MutableList<String> = ArrayList()
        val error = ex.parameterName + ", " + ex.message
        errors.add(error)
        val errorMessage = ErrorMessage(errors)
        return ResponseEntity<Any?>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    fun handleHttpMediaTypeNotSupported(
        ex: HttpMediaTypeNotSupportedException
    ): ResponseEntity<Any?> {
        val unsupported = "Unsupported content type: " + ex.contentType
        val supported = "Supported content types: " + MediaType.toString(ex.supportedMediaTypes)
        val errorMessage = ErrorMessage(unsupported, supported)
        return ResponseEntity<Any?>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<Any?> {
        val errorMessage = ErrorMessage(ex.message)
        return ResponseEntity<Any?>(errorMessage, HttpStatus.BAD_REQUEST)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GlobalControllerAdvice::class.java)
    }
}