package co.clean_architecture.usecase.order.exception;

import co.clean_architecture.model.exception.DomainException;

public class StatusOrderIsNotValidException extends DomainException {
    public StatusOrderIsNotValidException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "STATUS_ORDER_IS_NOT_VALID";
    }
}
