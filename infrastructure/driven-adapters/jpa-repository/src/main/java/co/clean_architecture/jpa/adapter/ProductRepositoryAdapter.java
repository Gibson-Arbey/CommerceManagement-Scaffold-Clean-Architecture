package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.ProductEntity;
import co.clean_architecture.jpa.repository.ProductJpaRepository;
import co.clean_architecture.model.product.Product;
import co.clean_architecture.model.product.criteria.ProductCriteria;
import co.clean_architecture.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product createProduct(Product product) {
        return mapProductEntityToProduct(
            productJpaRepository.save(
                mapProductToProductEntity(product)
            )
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productJpaRepository.findById(id)
            .map(this::mapProductEntityToProduct)
            .orElse(null);
    }

    @Override
    public List<Product> getProductsByFilters(ProductCriteria productCriteria) {
        return productJpaRepository.findByFilters(
            productCriteria.getName(),
            productCriteria.getAppliesCategoryFilter(),
            productCriteria.getCategoryIds(),
            productCriteria.getMinPrice(),
            productCriteria.getMaxPrice(),
            productCriteria.getStatus()
        ).stream()
        .map(this::mapProductEntityToProduct)
        .toList();
    }

    @Override
    public void deleteProductById(Long id) {
        productJpaRepository.deleteById(id);
    }

    private Product mapProductEntityToProduct(ProductEntity productEntity) {
        return Product.builder()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .price(productEntity.getPrice())
            .categoryId(productEntity.getCategory().getId())
            .status(productEntity.getStatus())
            .build();
    }

    private ProductEntity mapProductToProductEntity(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .status(product.getStatus())
            .build();
    }
}
