package co.clean_architecture.usecase.customer.exception;

import co.clean_architecture.model.exception.DomainException;

public class EmailCustomerExistsException extends DomainException {

    public EmailCustomerExistsException(String msg) {
        super(msg);
    }

    @Override
    public String getCode() {
        return "EMAIL_CUSTOMER_EXISTS";
    }
}