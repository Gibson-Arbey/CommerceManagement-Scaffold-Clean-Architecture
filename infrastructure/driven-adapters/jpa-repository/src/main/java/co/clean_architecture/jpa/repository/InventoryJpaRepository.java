package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {

    InventoryEntity findByProductId(Long productId);

    @Modifying
    @Query("DELETE FROM InventoryEntity i WHERE i.product.id = :productId")
    void deleteByProductId(Long productId);
}
