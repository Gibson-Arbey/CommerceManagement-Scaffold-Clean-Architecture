package co.clean_architecture.usecase.customer.exception;

import co.clean_architecture.model.exception.DomainException;

public class InvalidPhoneCustomerException extends DomainException {
    public InvalidPhoneCustomerException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVALID_PHONE_CUSTOMER";
    }
}
