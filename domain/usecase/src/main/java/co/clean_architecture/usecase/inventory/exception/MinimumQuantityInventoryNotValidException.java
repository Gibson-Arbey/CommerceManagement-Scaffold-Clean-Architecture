package co.clean_architecture.usecase.inventory.exception;

import co.clean_architecture.model.exception.DomainException;

public class MinimumQuantityInventoryNotValidException extends DomainException {

    public MinimumQuantityInventoryNotValidException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "QUANTITY_INVENTORY_NOT_VALID";
    }
}
