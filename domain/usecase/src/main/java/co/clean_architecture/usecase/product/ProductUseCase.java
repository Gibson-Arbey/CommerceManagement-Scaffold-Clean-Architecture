package co.clean_architecture.usecase.product;

import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.model.product.Product;
import co.clean_architecture.model.product.criteria.ProductCriteria;
import co.clean_architecture.model.product.gateways.ProductRepository;
import co.clean_architecture.model.product.exception.ProductNotFoundException;
import co.clean_architecture.usecase.product.exception.StatusProductIsNotValidException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDate.now());
        validateStatus(product.getStatus());
        validateCategoryExists(product.getCategoryId());
        return productRepository.createProduct(product);
    }

    public Product getProductById(Long id) {
        return validateExistsProduct(id);
    }

    public List<Product> getAllProductsByFilters(ProductCriteria productCriteria) {
        return productRepository.getProductsByFilters(productCriteria);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = validateExistsProduct(product.getId());
        product.setCreatedAt(existingProduct.getCreatedAt());
        validateStatus(product.getStatus());
        validateCategoryExists(product.getCategoryId());
        return productRepository.createProduct(product);
    }

    public void deleteProductById(Long id) {
        validateExistsProduct(id);
        productRepository.deleteProductById(id);
    }

    private Product validateExistsProduct(Long id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " does not exist.");
        }
        return product;
    }

    private void validateStatus(String status) {
        try {
            StatusProductEnum.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new StatusProductIsNotValidException(
                    "The status '" + status + "' is not valid. Allowed values: " + List.of(StatusProductEnum.values())
            );
        }
    }

    private void validateCategoryExists(Long categoryId) {
        if (categoryRepository.findById(categoryId) == null) {
            throw new CategoryNotFoundException("Category with ID " + categoryId + " does not exist.");
        }
    }
}
