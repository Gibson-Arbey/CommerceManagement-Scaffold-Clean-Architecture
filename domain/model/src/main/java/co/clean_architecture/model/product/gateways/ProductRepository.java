package co.clean_architecture.model.product.gateways;

import co.clean_architecture.model.product.Product;
import co.clean_architecture.model.product.criteria.ProductCriteria;

import java.util.List;

public interface ProductRepository {

    Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getProductsByFilters(ProductCriteria productCriteria);

    void deleteProductById(Long id);
}
