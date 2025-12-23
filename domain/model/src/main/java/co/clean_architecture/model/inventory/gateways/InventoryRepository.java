package co.clean_architecture.model.inventory.gateways;

import co.clean_architecture.model.inventory.Inventory;

public interface InventoryRepository {

    Inventory saveInventory(Inventory inventory);

    Inventory getInventoryById(Long inventoryId);

    Inventory getInventoryByProductId(Long productId);

    void deleteInventoryByProductId(Long productId);

}
