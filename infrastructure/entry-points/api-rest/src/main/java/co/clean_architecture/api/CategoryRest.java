package co.clean_architecture.api;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.usecase.category.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryRest {

    private final CategoryUseCase categoryUseCase;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryUseCase.createCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryUseCase.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<java.util.List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryUseCase.getAllCategories());
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryUseCase.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id) {
        categoryUseCase.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}

