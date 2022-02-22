package com.nisum.project.infrastructure.exception;

import com.nisum.project.infrastructure.resource.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Configuration
@PropertySource("classpath:parameters.properties")
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    @Value("${message.error.validate}")
    private String messageError;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final List<String> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            final String detail = ((FieldError) error)
                    .getField().concat(" : ").concat(error.getDefaultMessage());
            details.add(detail);
        });
        return new ResponseEntity(ErrorResponse.error(status.value(),
                this.messageError, details),
                status);
    }
}
