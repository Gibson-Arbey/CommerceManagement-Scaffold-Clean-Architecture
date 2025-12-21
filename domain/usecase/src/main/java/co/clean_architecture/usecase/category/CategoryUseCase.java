package co.clean_architecture.usecase.category;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import co.clean_architecture.model.category.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        category.setCreatedAt(LocalDate.now());
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return validateCategoryExists(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category) {
        Category existingCategory = validateCategoryExists(category.getId());
        category.setCreatedAt(existingCategory.getCreatedAt());
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long cateId) {
        validateCategoryExists(cateId);
        categoryRepository.deleteById(cateId);
    }

    private Category validateCategoryExists(Long cateId) {
        Category category = categoryRepository.findById(cateId);
        if (category == null) {
            throw new CategoryNotFoundException("Category with ID " + cateId + " not found.");
        }
        return category;
    }
}
