package com.user.user_app.common.exception.interfaces.rest;

import com.user.user_app.common.exception.interfaces.dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class ExceptionWebController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleTransactionSystemException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMethod(request.getMethod());
        response.setResource(request.getRequestURI());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setTitle("Erro na validação da estrutura dos dados");
        response.setSummary("O conteúdo da requisição contém " + exception.getBindingResult().getFieldErrors().size() + " erro(s) de validação");
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            response.getDetails().add(error.getDefaultMessage() + " (Campo: " + error.getField() + ")");
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
