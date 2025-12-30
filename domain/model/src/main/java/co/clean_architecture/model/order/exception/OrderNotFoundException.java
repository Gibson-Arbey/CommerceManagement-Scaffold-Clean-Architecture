package co.clean_architecture.model.order.exception;

import co.clean_architecture.model.exception.DomainException;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "ORDER_NOT_FOUND";
    }
}
