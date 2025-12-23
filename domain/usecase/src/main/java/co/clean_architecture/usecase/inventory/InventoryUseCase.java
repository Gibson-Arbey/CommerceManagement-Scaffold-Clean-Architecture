package co.clean_architecture.usecase.inventory;

import co.clean_architecture.model.inventory.Inventory;
import co.clean_architecture.model.inventory.InventoryNotFoundException;
import co.clean_architecture.model.inventory.gateways.InventoryRepository;
import co.clean_architecture.model.product.exception.ProductNotFoundException;
import co.clean_architecture.model.product.gateways.ProductRepository;
import co.clean_architecture.usecase.inventory.exception.MinimumQuantityInventoryNotValidException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class InventoryUseCase {

    private final InventoryRepository inventoryRepository;

    private final ProductRepository productRepository;

    public Inventory createInventory(Inventory inventory) {
        inventory.setCreatedAt(LocalDate.now());
        validateProductExists(inventory.getProductId());
        validateMinimumQuantity(inventory.getQuantity(), inventory.getMinimumQuantity());
        return inventoryRepository.saveInventory(inventory);
    }

    public Inventory getInventoryByProductId(Long productId) {
        validateProductExists(productId);
        return inventoryRepository.getInventoryByProductId(productId);
    }

    public void deleteInventoryByProductId(Long productId) {
        validateProductExists(productId);
        inventoryRepository.deleteInventoryByProductId(productId);
    }

    public Inventory updateInventory(Inventory inventory) {
        validateProductExists(inventory.getProductId());
        Inventory existingInventory = validateExistsInventory(inventory.getId());
        validateMinimumQuantity(inventory.getQuantity(), inventory.getMinimumQuantity());
        inventory.setCreatedAt(existingInventory.getCreatedAt());
        return inventoryRepository.saveInventory(inventory);
    }

    private void validateProductExists(Long productId) {
        if (productRepository.getProductById(productId) == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " does not exist.");
        }
    }

    private Inventory validateExistsInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.getInventoryById(inventoryId);
        if (inventory == null) {
            throw new InventoryNotFoundException("Inventory with ID " + inventoryId + " does not exist.");
        }
        return inventory;
    }

    private void validateMinimumQuantity(Integer quantity, Integer minimumQuantity) {
        if( quantity == null || minimumQuantity == null) {
            throw new MinimumQuantityInventoryNotValidException("Quantity and minimum quantity must not be null.");
        }

        if (minimumQuantity < 0 || minimumQuantity > quantity) {
            throw new MinimumQuantityInventoryNotValidException("Minimum quantity must be non-negative and less than or equal to quantity.");
        }
    }

}
