package com.user.user_app.common.exception.interfaces.rest;

import com.user.user_app.common.exception.annotation.ExceptionMapping;
import com.user.user_app.common.exception.interfaces.dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(2)
@RestControllerAdvice
public class ExceptionDefaultController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleDefaultException(HttpServletRequest request, Exception exception) throws Exception {
        if (exception.getClass().getAnnotation(ResponseStatus.class) != null){
            throw exception;
        }

        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setResource(request.getRequestURI());
        response.setMethod(request.getMethod());
        response.setTitle("Exceção em tempo de execução do sistema");
        response.setSummary("O conteúdo da requisição contém erros que levaram a uma exceção de sistema");

        HttpStatus status;

        ExceptionMapping exceptionMapping = exception.getClass().getAnnotation(ExceptionMapping.class);
        if (exceptionMapping != null){
            status = exceptionMapping.statusCode();
            response.setStatusCode(status.value());
            response.setReasonPhrase(status.getReasonPhrase());
            response.getDetails().add(exceptionMapping.message());
        } else {
            status = HttpStatus.BAD_REQUEST;
            response.setStatusCode(status.value());
            response.setReasonPhrase(status.getReasonPhrase());
            response.getDetails().add(exception.getMessage());
        }

        exception.printStackTrace();

        return new ResponseEntity<>(response, status);
    }
}
