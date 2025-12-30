package co.clean_architecture.api.config;

import co.clean_architecture.api.dto.ErrorResponse;
import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import co.clean_architecture.model.exception.DomainException;
import co.clean_architecture.model.product.exception.ProductNotFoundException;
import co.clean_architecture.usecase.product.exception.StatusProductIsNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(DomainException ex) {
        return ResponseEntity.badRequest().body(
            ErrorResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error(ex.getMessage());
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String code,
            String message
    ) {
        return ResponseEntity.status(status).body(
                ErrorResponse.builder()
                        .code(code)
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
