package lk.ijse.carrental.service.custom;



import lk.ijse.carrental.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDto categoryDto);

    CategoryDto search(String catId);

    List<CategoryDto> getAll();

    void updateCategory(CategoryDto catDto);
}
