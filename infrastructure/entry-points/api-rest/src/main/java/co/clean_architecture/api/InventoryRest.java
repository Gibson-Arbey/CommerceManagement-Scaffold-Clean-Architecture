package co.clean_architecture.api;

import co.clean_architecture.model.inventory.Inventory;
import co.clean_architecture.usecase.inventory.InventoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryRest {

    private final InventoryUseCase inventoryUseCase;

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryUseCase.createInventory(inventory));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(inventoryUseCase.getInventoryByProductId(productId));
    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryUseCase.updateInventory(inventory));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteInventoryByProductId(@PathVariable("productId") Long productId) {
        inventoryUseCase.deleteInventoryByProductId(productId);
        return ResponseEntity.noContent().build();
    }
}
