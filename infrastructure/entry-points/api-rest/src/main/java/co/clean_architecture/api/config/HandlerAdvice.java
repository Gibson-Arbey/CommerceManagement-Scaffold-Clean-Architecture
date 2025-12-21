package co.clean_architecture.api.config;

import co.clean_architecture.api.dto.ErrorResponse;
import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import co.clean_architecture.model.product.exception.ProductNotFoundException;
import co.clean_architecture.usecase.product.exception.StatusProductIsNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "PRODUCT_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "CATEGORY_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(StatusProductIsNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidStatus(StatusProductIsNotValidException ex) {
        return build(HttpStatus.BAD_REQUEST, "INVALID_PRODUCT_STATUS", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
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
