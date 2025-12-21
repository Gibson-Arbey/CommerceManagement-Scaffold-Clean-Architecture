package co.clean_architecture.model.address.exception;

import co.clean_architecture.model.exception.DomainException;

public class AddressNotFoundException extends DomainException {

    public AddressNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "ADDRESS_NOT_FOUND";
    }
}
