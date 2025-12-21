package co.clean_architecture.model.product.exception;

import co.clean_architecture.model.exception.DomainException;

public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "PRODUCT_NOT_FOUND";
    }
}
