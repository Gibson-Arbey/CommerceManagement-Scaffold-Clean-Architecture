package co.clean_architecture.model.inventory;

import co.clean_architecture.model.exception.DomainException;

public class InventoryNotFoundException extends DomainException {

    public InventoryNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "INVENTORY_NOT_FOUND";
    }
}
