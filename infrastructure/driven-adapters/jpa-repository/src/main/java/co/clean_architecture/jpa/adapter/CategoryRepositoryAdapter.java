package co.clean_architecture.jpa.adapter;

import co.clean_architecture.jpa.entity.CategoryEntity;
import co.clean_architecture.jpa.repository.CategoryJpaRepository;
import co.clean_architecture.model.category.Category;
import co.clean_architecture.model.category.gateways.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category save(Category category) {
        return mapCategoryEntityToCategory(
                categoryJpaRepository.save(
                        mapCategoryToCategoryEntity(category))
        );
    }

    @Override
    public Category findById(Long id) {
        return categoryJpaRepository.findById(id)
                .map(this::mapCategoryEntityToCategory)
                .orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository
                .findAll()
                .stream()
                .map(this::mapCategoryEntityToCategory).toList();
    }

    @Override
    public void deleteById(Long id) {
        categoryJpaRepository.deleteById(id);
    }

    private Category mapCategoryEntityToCategory(CategoryEntity categoryEntity) {
        return Category
            .builder()
            .id(categoryEntity.getId())
            .name(categoryEntity.getName())
            .description(categoryEntity.getDescription())
            .createdAt(categoryEntity.getCreatedAt())
            .build();
    }

    private CategoryEntity mapCategoryToCategoryEntity(Category category) {
        return CategoryEntity
            .builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .createdAt(category.getCreatedAt())
            .build();
    }
}
