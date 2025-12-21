package co.clean_architecture.usecase.product.exception;

import co.clean_architecture.model.exception.DomainException;

public class StatusProductIsNotValidException extends DomainException {
    public StatusProductIsNotValidException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "STATUS_PRODUCT_IS_NOT_VALID";
    }
}
