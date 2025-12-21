package co.clean_architecture.usecase.customer.exception;

import co.clean_architecture.model.exception.DomainException;

public class StatusCustomerIsNotValidException extends DomainException {

    public StatusCustomerIsNotValidException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "STATUS_CUSTOMER_IS_NOT_VALID";
    }
}
