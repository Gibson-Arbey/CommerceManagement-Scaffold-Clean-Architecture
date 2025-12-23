package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.InventoryEntity;
import co.clean_architecture.jpa.entity.ProductEntity;
import co.clean_architecture.jpa.repository.InventoryJpaRepository;
import co.clean_architecture.model.inventory.Inventory;
import co.clean_architecture.model.inventory.gateways.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryRepository {

    private  final InventoryJpaRepository inventoryJpaRepository;

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return mapInventoryEntityToInventory(inventoryJpaRepository.save(
            mapInventoryToInventoryEntity(inventory)
        ));
    }

    @Override
    public Inventory getInventoryById(Long inventoryId) {
        return inventoryJpaRepository.findById(inventoryId)
            .map(this::mapInventoryEntityToInventory)
            .orElse(null);
    }

    @Override
    public Inventory getInventoryByProductId(Long productId) {
        return mapInventoryEntityToInventory(
            inventoryJpaRepository.findByProductId(productId)
        );
    }

    @Override
    public void deleteInventoryByProductId(Long productId) {
        inventoryJpaRepository.deleteByProductId(productId);
    }

    private Inventory mapInventoryEntityToInventory(InventoryEntity inventoryEntity) {
        return Inventory.builder()
            .id(inventoryEntity.getId())
            .productId(inventoryEntity.getProduct().getId())
            .quantity(inventoryEntity.getQuantity())
            .minimumQuantity(inventoryEntity.getMinimumQuantity())
            .createdAt(inventoryEntity.getCreatedAt())
            .build();
    }

    private InventoryEntity mapInventoryToInventoryEntity(Inventory inventory) {
        return InventoryEntity.builder()
            .id(inventory.getId())
                .product(new ProductEntity(inventory.getProductId()))
            .quantity(inventory.getQuantity())
            .minimumQuantity(inventory.getMinimumQuantity())
            .createdAt(inventory.getCreatedAt())
            .build();
    }
}
