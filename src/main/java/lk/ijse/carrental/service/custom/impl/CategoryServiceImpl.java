package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.CategoryDao;
import lk.ijse.carrental.dto.CategoryDto;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.service.custom.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = DaoFactory.getDao(DaoType.CATEGORY);

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        var categoryEntity = new CategoryEntity(categoryDto.getId(), categoryDto.getName());

        categoryDao.save(categoryEntity);
    }

    @Override
    public CategoryDto search(String catId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAll() {
        return null;
    }

    @Override
    public void updateCategory(CategoryDto catDto) {

    }
}