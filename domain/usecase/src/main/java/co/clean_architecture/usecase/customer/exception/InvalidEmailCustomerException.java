package co.clean_architecture.usecase.customer.exception;

import co.clean_architecture.model.exception.DomainException;

public class InvalidEmailCustomerException extends DomainException {
    public InvalidEmailCustomerException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_EMAIL_CUSTOMER";
    }
}
