package co.clean_architecture.model.category.gateways;

import co.clean_architecture.model.category.Category;

import java.util.List;

public interface CategoryRepository {

    Category save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void deleteById(Long id);
}
