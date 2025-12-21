package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("""
        SELECT p FROM ProductEntity p
        WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:appliesCategoryFilter = false OR p.category.id IN :categoryIds)
        AND (:minPrice IS NULL OR p.price >= :minPrice)
        AND (:maxPrice IS NULL OR p.price <= :maxPrice)
        AND (:status IS NULL OR p.status = :status)
        """)
    List<ProductEntity> findByFilters(
        @Param("name") String name,
        @Param("appliesCategoryFilter") boolean appliesCategoryFilter,
        @Param("categoryIds") List<Long> categoryIds,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        @Param("status") String status
    );


}
