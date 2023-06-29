package org.yearup.data;

import org.springframework.http.ResponseEntity;
import org.yearup.models.Category;

import java.util.List;

public interface CategoryDao
{
    List<Category> getAllCategories();

    Category getById(int categoryId);
    ResponseEntity<Category> create(Category category);

    void update(int categoryId, Category category);
    void delete(int categoryId);
}
