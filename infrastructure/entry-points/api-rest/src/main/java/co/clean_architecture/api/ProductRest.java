package co.clean_architecture.api;

import co.clean_architecture.model.product.Product;
import co.clean_architecture.model.product.criteria.ProductCriteria;
import co.clean_architecture.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductRest {

    private final ProductUseCase productUseCase;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productUseCase.createProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productUseCase.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductsByFilters(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "categoryIds", required = false) List<Long> categoryIds,
        @RequestParam(name = "minPrice", required = false) Double minPrice,
        @RequestParam(name = "maxPrice", required = false) Double maxPrice,
        @RequestParam(name = "status", required = false) String status
    ) {
        ProductCriteria criteria = ProductCriteria.builder()
                .name(name)
                .categoryIds(categoryIds)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .status(status)
                .build();

        return ResponseEntity.ok(
                productUseCase.getAllProductsByFilters(criteria)
        );
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productUseCase.updateProduct(product));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Product> updateStatusProduct(@PathVariable("id") Long id, @RequestBody String status) {
        productUseCase.updateStatusProduct(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productUseCase.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
